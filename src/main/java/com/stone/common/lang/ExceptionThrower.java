package com.stone.common.lang;

import static java.lang.String.format;

import java.util.Collection;
import java.util.List;

public class ExceptionThrower<T extends Throwable> {

    public static <T extends Throwable> ExceptionThrower<T> instance(final Class<T> type) {

        return new ExceptionThrower<T>(type);
    }

    private final Class<T> throwableClass;

    public ExceptionThrower(final Class<T> throwableClass) {

        this.throwableClass = throwableClass;
    }

    public <U extends Object> void throwIfAnyNull(final List<U> objects, final Object... params)
            throws T {

        for (U object : objects) {

            throwIfNull(object, params);
        }
    }

    public void throwIfNullS(final Object object, final String formatString,
            final Object... stringFormatParams) throws T {

        String message = format(formatString, stringFormatParams);
        ExceptionUtils.throwIfNull(object, throwableClass, message);
    }

    public void throwIfNullS(final Object object, final Throwable cause, final String formatString,
            final Object... stringFormatParams) throws T {

        String message = format(formatString, stringFormatParams);
        ExceptionUtils.throwIfNull(object, throwableClass, message, cause);
    }

    public void throwIfNull(final Object object, final Object... params) throws T {

        ExceptionUtils.throwIfNull(object, throwableClass, params);
    }

    public void throwIfNotNull(final Object object, final Object... params) throws T {

        ExceptionUtils.throwIfNotNull(object, throwableClass, params);
    }

    public void throwIfNotEqual(final Object o1, final Object o2, final Object... params) throws T {

        ExceptionUtils.throwIfFalse(o1.equals(o2), throwableClass, params);
    }

    public void throwIfTrue(final boolean condition, final Object... params) throws T {

        ExceptionUtils.throwIfTrue(condition, throwableClass, params);
    }

    public void throwIfTrueS(final boolean condition, final String formatString,
            final Object... stringFormatParams) throws T {

        String message = format(formatString, stringFormatParams);
        ExceptionUtils.throwIfTrue(condition, throwableClass, message);
    }

    public void throwIfFalse(final boolean condition, final Object... params) throws T {

        ExceptionUtils.throwIfFalse(condition, throwableClass, params);
    }

    public void throwIfAnyEmpty(final List<String> strings, final Object... params) throws T {

        for (String string : strings) {

            throwIfEmpty(string, params);
        }
    }

    public void throwIfEmpty(final String string, final Object... params) throws T {

        ExceptionUtils.throwIfEmpty(string, throwableClass, params);
    }

    public void throwIfEmpty(final Collection<?> collection, final Object... params) throws T {

        ExceptionUtils.throwIfEmpty(collection, throwableClass, params);
    }

    public void throwNow(final Object... params) throws T {

        ExceptionUtils.throwNow(throwableClass, params);
    }

    public void throwNowS(final String formatMessage, final Object... messageParams) throws T {

        String errorMessage = format(formatMessage, messageParams);
        ExceptionUtils.throwNow(throwableClass, errorMessage);
    }

    public void throwNowS(final Throwable cause) throws T {

        ExceptionUtils.throwNow(throwableClass, cause);
    }

    public void throwNowS(final Throwable cause, final String formatMessage,
            final Object... messageParams) throws T {

        String errorMessage = format(formatMessage, messageParams);
        ExceptionUtils.throwNow(throwableClass, errorMessage, cause);
    }

    public T create(final Object... params) {

        return ExceptionUtils.create(throwableClass, params);
    }

    public T createS(final Throwable cause, final String formatMessage,
            final Object... messageParams) {

        String errorMessage = format(formatMessage, messageParams);
        return ExceptionUtils.create(throwableClass, errorMessage, cause);
    }
}
