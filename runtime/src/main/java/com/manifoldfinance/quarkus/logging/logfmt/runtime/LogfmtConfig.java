package com.manifoldfinance.quarkus.logging.logfmt.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import org.jboss.logmanager.formatters.StructuredFormatter;

@ConfigRoot(phase = ConfigPhase.RUN_TIME, name = "log.console.logfmt")
public class LogfmtConfig {

    /**
     * Determine whether to enable the Logfmt console formatting extension, which disables "normal" console formatting.
     */
    @ConfigItem(name = ConfigItem.PARENT, defaultValue = "true")
    boolean enable;

    /**
     * The date format to use. The special string "default" indicates that the default format should be used.
     */
    @ConfigItem(defaultValue = "default")
    String dateFormat;

    /**
     * The exception output type to specify.
     */
    @ConfigItem(defaultValue = "detailed")
    StructuredFormatter.ExceptionOutputType exceptionOutputType;

    /**
     * The zone ID to use. The special string "default" indicates that the default zone should be used.
     */
    @ConfigItem(defaultValue = "default")
    String zoneId;

    /**
     * Enable printing of more details in the log.
     * <p>
     * Printing the details can be expensive as the values are retrieved from the caller. The details include the
     * source class name, source file name, source method name and source line number.
     */
    @ConfigItem
    boolean printDetails;
}
