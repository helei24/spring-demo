package com.stone.common.lang;

public class ErrorMessages {

    public static String notNullMessage() {

        return notNullMessage(Object.class);
    }

    public static String notNullMessage(final Class<?> type) {

        return (type != null ? type.getSimpleName() : "Unspecified type") + " cannot be null";
    }

    public static String notEmptyMessage(final String parameter) {

        return parameter + " cannot be empty";
    }

    public static String notInitializedMessage(final Class<?> type) {

        return (type != null ? type.getSimpleName() : "Unspecified type")
                + " has not been initialized";
    }

    public static <T extends Object> String geMessage(final String name, final T value,
            final T lowerLimit) {

        return String.format("Variable [%s] with value %s should be greater than or equal to %s",
                name, toStringOrNull(value), toStringOrNull(lowerLimit));
    }

    public static <T extends Number> String gtMessage(final String name, final T value,
            final T lowerLimit) {

        return String.format("Variable [%s] with value %s should be greater than %s", name,
                toStringOrNull(value), toStringOrNull(lowerLimit));
    }

    public static <T extends Object> String leMessage(final String name, final T value,
            final T upperLimit) {

        return String.format("Variable [%s] with value %s should be less than or equal to %s",
                name, toStringOrNull(value), toStringOrNull(upperLimit));
    }

    public static <T extends Object> String ltMessage(final String name, final T value,
            final T upperLimit) {

        return String.format("Variable [%s] with value %s should be less than %s", name,
                toStringOrNull(value), toStringOrNull(upperLimit));
    }

    static <T> String toStringOrNull(final T value) {

        return value == null ? null : value.toString();
    }
}
