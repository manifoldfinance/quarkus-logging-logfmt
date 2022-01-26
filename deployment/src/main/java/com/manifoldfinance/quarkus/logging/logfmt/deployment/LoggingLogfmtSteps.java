package com.manifoldfinance.quarkus.logging.logfmt.deployment;

import com.manifoldfinance.quarkus.logging.logfmt.runtime.LogfmtConfig;
import com.manifoldfinance.quarkus.logging.logfmt.runtime.LoggingLogfmtRecorder;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.LogConsoleFormatBuildItem;

public class LoggingLogfmtSteps {

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    public LogConsoleFormatBuildItem setUpFormatter(LoggingLogfmtRecorder recorder, LogfmtConfig config) {
        return new LogConsoleFormatBuildItem(recorder.initialiseLogfmtLogging(config));
    }

}
