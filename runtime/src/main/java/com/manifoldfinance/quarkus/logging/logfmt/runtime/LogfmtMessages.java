package com.manifoldfinance.quarkus.logging.logfmt.runtime;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class LogfmtMessages {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("com.manifoldfinance.quarkus.logging.logfmt.messages");

    LogfmtMessages() {
    }

    static String GENERATOR_FLUSH_IO_ERR() {
        return localize("generator.flush.io.err");
    }

    static String GENERATOR_CLOSE_IO_ERR() {
        return localize("generator.close.io.err");
    }

    static String GENERATOR_WRITE_IO_ERR() {
        return localize("generator.write.io.err");
    }

    private static String localize(String key, Object... args) {
        try {
            String msg = BUNDLE.getString(key);
            return MessageFormat.format(msg, args);
        } catch (Exception var3) {
            return getDefaultMessage(key, args);
        }
    }

    private static String getDefaultMessage(String key, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("[failed to localize] ");
        sb.append(key);
        if (args != null) {
            sb.append('(');

            for(int i = 0; i < args.length; ++i) {
                if (i != 0) {
                    sb.append(", ");
                }

                sb.append(String.valueOf(args[i]));
            }

            sb.append(')');
        }

        return sb.toString();
    }

}
