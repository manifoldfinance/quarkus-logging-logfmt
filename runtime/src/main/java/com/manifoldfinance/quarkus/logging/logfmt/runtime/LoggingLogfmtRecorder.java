package com.manifoldfinance.quarkus.logging.logfmt.runtime;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

import java.util.Optional;
import java.util.logging.Formatter;

@Recorder
public class LoggingLogfmtRecorder {

    public RuntimeValue<Optional<Formatter>> initialiseLogfmtLogging(final LogfmtConfig config) {
        if(!config.enable) {
            return new RuntimeValue<>(Optional.empty());
        }
        final LogfmtFormatter formatter = new LogfmtFormatter();
        final String dateFormat = config.dateFormat;
        if(!dateFormat.equals("default")) {
            formatter.setDateFormat(dateFormat);
        }
        formatter.setExceptionOutputType(config.exceptionOutputType);
        formatter.setPrintDetails(config.printDetails);
        final String zoneId = config.zoneId;
        if (!zoneId.equals("default")) {
            formatter.setZoneId(zoneId);
        }
        return new RuntimeValue<>(Optional.of(formatter));
    }

}
