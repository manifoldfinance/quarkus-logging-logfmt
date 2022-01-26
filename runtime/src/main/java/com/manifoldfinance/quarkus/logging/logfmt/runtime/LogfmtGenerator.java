package com.manifoldfinance.quarkus.logging.logfmt.runtime;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class LogfmtGenerator implements Flushable, Closeable {

    private final DateTimeFormatter dateTimeFormatter;
    private final StringBuilder builder;

    private final Writer writer;

    public LogfmtGenerator(DateTimeFormatter dateTimeFormatter, Writer writer) {
        this.dateTimeFormatter = dateTimeFormatter;
        this.writer = writer;
        this.builder = new StringBuilder(128);
    }

    private StringBuilder writeKey(String key) {
        return builder
                .append(" ")
                .append(key.replaceAll(" ", "%"))
                .append("=");
    }

    LogfmtGenerator writeString(String key, String value) {
        String escaped = value
                .replaceAll("\n", "\\\\n")
                .replaceAll("\"", "\\\\\"");

        if (escaped.contains(" ")) {
            escaped = "\"" + escaped + "\"";
        }

        writeKey(key).append(escaped);

        return this;
    }

    LogfmtGenerator writeBoolean(String key, boolean value) {
        writeKey(key).append(value);
        return this;
    }

    LogfmtGenerator writeInt(String key, int value) {
        writeKey(key).append(value);
        return this;
    }

    LogfmtGenerator writeLong(String key, long value) {
        writeKey(key).append(value);
        return this;
    }

    StringBuilder writeDouble(String key, double value) {
        writeKey(key).append(value);
        return builder;
    }

    LogfmtGenerator writeBigInteger(String key, BigInteger value) {
        // todo make radix configurable
        writeKey(key)
                .append("0x")
                .append(value.toString(16));
        return this;
    }

    LogfmtGenerator writeDateTime(String key, Temporal value) {
        // todo make radix configurable
        writeKey(key).append(dateTimeFormatter.format(value));
        return this;
    }

    LogfmtGenerator write(String key, Object value) {
        // write an escaped version of the string representation of the object
        if (value instanceof Boolean) {
            writeBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            writeInt(key, (Integer) value);
        } else if (value instanceof Long) {
            writeLong(key, (Long) value);
        } else if (value instanceof Double) {
            writeDouble(key, (Double) value);
        } else if (value instanceof BigInteger) {
            writeBigInteger(key, (BigInteger) value);
        } else if (value instanceof Temporal) {
            writeDateTime(key, (Temporal) value);
        } else {
            writeString(key, value.toString());
        }
        return this;
    }

    private void flushBuffer() {
        try {
            if (builder.length() > 0) {
                // add record delimiter
                builder.append("\n");
                // skip the leading space
                writer.write(builder.toString(), 1, builder.length());
                builder.delete(0, builder.length());
            }
        } catch (IOException var2) {
            throw new LogfmtException(LogfmtMessages.GENERATOR_WRITE_IO_ERR(), var2);
        }
    }

    @Override
    public void flush() throws IOException {
        flushBuffer();
        try {
            this.writer.flush();
        } catch (IOException ex) {
            throw new LogfmtException(LogfmtMessages.GENERATOR_FLUSH_IO_ERR(), ex);
        }
    }

    @Override
    public void close() throws IOException {
        flushBuffer();
        try {
            this.writer.close();
        } catch (IOException ex) {
            throw new LogfmtException(LogfmtMessages.GENERATOR_CLOSE_IO_ERR(), ex);
        }
    }

}
