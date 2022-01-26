package com.manifoldfinance.quarkus.logging.logfmt.runtime;
public final class LogfmtConfig$$accessor {
    private LogfmtConfig$$accessor() {}
    @SuppressWarnings("unchecked")
    public static boolean get_enable(Object __instance) {
        return ((LogfmtConfig)__instance).enable;
    }
    @SuppressWarnings("unchecked")
    public static void set_enable(Object __instance, boolean enable) {
        ((LogfmtConfig)__instance).enable = enable;
    }
    @SuppressWarnings("unchecked")
    public static Object get_dateFormat(Object __instance) {
        return ((LogfmtConfig)__instance).dateFormat;
    }
    @SuppressWarnings("unchecked")
    public static void set_dateFormat(Object __instance, Object dateFormat) {
        ((LogfmtConfig)__instance).dateFormat = (String)dateFormat;
    }
    @SuppressWarnings("unchecked")
    public static Object get_exceptionOutputType(Object __instance) {
        return ((LogfmtConfig)__instance).exceptionOutputType;
    }
    @SuppressWarnings("unchecked")
    public static void set_exceptionOutputType(Object __instance, Object exceptionOutputType) {
        ((LogfmtConfig)__instance).exceptionOutputType = (org.jboss.logmanager.formatters.StructuredFormatter.ExceptionOutputType)exceptionOutputType;
    }
    @SuppressWarnings("unchecked")
    public static Object get_zoneId(Object __instance) {
        return ((LogfmtConfig)__instance).zoneId;
    }
    @SuppressWarnings("unchecked")
    public static void set_zoneId(Object __instance, Object zoneId) {
        ((LogfmtConfig)__instance).zoneId = (String)zoneId;
    }
    @SuppressWarnings("unchecked")
    public static boolean get_printDetails(Object __instance) {
        return ((LogfmtConfig)__instance).printDetails;
    }
    @SuppressWarnings("unchecked")
    public static void set_printDetails(Object __instance, boolean printDetails) {
        ((LogfmtConfig)__instance).printDetails = printDetails;
    }
}
