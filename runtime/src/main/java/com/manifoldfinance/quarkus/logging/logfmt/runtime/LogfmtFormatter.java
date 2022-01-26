package com.manifoldfinance.quarkus.logging.logfmt.runtime;

import org.jboss.logmanager.formatters.StructuredFormatter;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LogfmtFormatter extends StructuredFormatter {

    @Override
    protected Generator createGenerator(Writer writer) throws Exception {
        return new FormatterLogfmtGenerator(new LogfmtGenerator(getDateTimeFormatter(), writer));
    }

    private class FormatterLogfmtGenerator implements Generator {

        private final LogfmtGenerator generator;

        private Stack<String> prefixes;

        private FormatterLogfmtGenerator(LogfmtGenerator generator) {
            this.generator = generator;
            this.prefixes = new Stack<>();
        }

        private String keyWithPrefixes(String key) {
            if (prefixes.empty()) return key;
            final StringBuilder builder = new StringBuilder();
            for (String prefix : prefixes) {
                builder.append(prefix)
                        .append("_");
            }
            return builder.append(key).toString();
        }

        public Generator add(String key, int value) {
            generator.writeInt(keyWithPrefixes(key), value);
            return this;
        }

        public Generator add(String key, long value) {
            generator.writeLong(keyWithPrefixes(key), value);
            return this;
        }

        @Override
        public Generator add(String key, String value) throws Exception {
            generator.writeString(keyWithPrefixes(key), value);
            return this;
        }

        @Override
        public Generator add(String key, Map<String, ?> value) throws Exception {
            for (String subKey : value.keySet()) {
                final Object subValue = value.get(subKey);
                generator.write(keyWithPrefixes(key) + "_" + subKey, subValue);
            }
            return this;
        }

        @Override
        public Generator startObject(String key) throws Exception {
            prefixes.push(key);
            return this;
        }

        @Override
        public Generator endObject() throws Exception {
            prefixes.pop();
            return this;
        }

        @Override
        public Generator end() throws Exception {
            generator.flush();
            generator.close();
            return this;
        }
    }

}
