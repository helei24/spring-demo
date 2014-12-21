package com.stone.common.lang;

import static java.lang.String.format;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.reflect.ConstructorUtils;

/**
 * Utilities for working with Exceptions
 * 
 * @author Jaran Nilsen
 * @author Bent André Solheim
 * @since 1.0
 * @version $Revision$
 */
public class ExceptionUtils {

    /**
     * Traverses through all the messages of the throwable and it's cause and
     * creates one String containing all messages.
     * 
     * @param e
     *            Throwable to generate nested message for.
     * @return
     */
    public static String createNestedMessagesFromThrowable(final Throwable e) {

        String message = "";
        Throwable t = e;
        while (t != null) {

            message += "-------- " + t.getClass().getName() + " --------\n";
            if (t.getMessage() != null)
                message += t.getMessage() + "\n\n";
            else
                message += "No message available for this thrown object.\n\n";

            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        e.printStackTrace(writer);
        writer.close();
        message += "-------- Stack Trace --------\n" + sw.getBuffer();

        return message;
    }

    public static <T, U extends T> List<T> args(final U... objects) {

        List<T> objectList = new ArrayList<T>();
        for (U object : objects) {
            objectList.add(object);
        }

        return objectList;
    }

    /**
     * Throws an instance of the <code>throwable</code> class if
     * <code>object</code> is <code>null</code>. The <code>params</code> array
     * are used as parameters to the <code>throwable</code> class constructor.
     * If an error occurs while instantiating or throwing the specified
     * {@link Throwable} class, the {@link Exception}/{@link Throwable} that
     * caused the error is returned from this method, and no {@link Throwable}
     * is thrown.
     * 
     * @param <T>
     *            The type of the {@link Throwable} to throw.
     * @param object
     *            The object that must be <code>null</code> for the
     *            {@link Throwable} to be thrown.
     * @param throwable
     *            The class of the {@link Throwable} to throw.
     * @param params
     *            The parameters to pass to the <code>throwable</code>
     *            constructor. The types of the <code>params</code> objects
     *            determines what constructor is called.
     * @return if <code>object</code> is not <code>null</code>,
     *         <code>null</code> is returned. If an {@link Exception}/
     *         {@link Throwable} occurs while instantiating the
     *         {@link Throwable} instance, this {@link Exception}/
     *         {@link Throwable} is returned. Under all other circumstances,
     *         this method will not get the opportunity to return anything, as
     *         it will throw a {@link T}.
     * @throws T
     *             if <code>object</code> is <code>null</code>.
     */
    public static <T extends Throwable> Throwable throwIfNull(final Object object,
            final Class<T> throwable, final Object... params) throws T {

        return throwIfTrue(object == null, throwable, params);
    }

    public static <T extends Throwable> Throwable throwIfNotNull(final Object object,
            final Class<T> throwable, final Object... params) throws T {

        return throwIfTrue(object != null, throwable, params);
    }

    public static <T extends Throwable> Throwable throwIfFalse(final boolean condition,
            final Class<T> throwable, final Object... params) throws T {

        return throwIfTrue(!condition, throwable, params);
    }

    public static <T extends Throwable> Throwable throwIfEmpty(final String string,
            final Class<T> throwable, final Object... params) throws T {

        boolean isEmpty = string == null || string.trim().length() == 0;
        return throwIfTrue(isEmpty, throwable, params);
    }

    public static <T extends Throwable> Throwable throwIfEmpty(final Collection<?> collection,
            final Class<T> throwable, final Object... params) throws T {

        boolean isEmpty = collection == null || collection.isEmpty();
        return throwIfTrue(isEmpty, throwable, params);
    }

    /**
     * Throws an instance of the <code>throwable</code> class if
     * <code>condition</code> is <code>true</code>. The <code>params</code>
     * array are used as parameters to the <code>throwable</code> class
     * constructor. If an error occurs while instantiating or throwing the
     * specified {@link Throwable} class, the {@link Exception}/
     * {@link Throwable} that caused the error is returned from this method, and
     * no {@link Throwable} is thrown.
     * 
     * @param <T>
     *            The type of the {@link Throwable} to throw.
     * @param condition
     *            The condition that must be <code>true</code> for the
     *            {@link Throwable} to be thrown.
     * @param throwable
     *            The class of the {@link Throwable} to throw.
     * @param params
     *            The parameters to pass to the <code>throwable</code>
     *            constructor. The types of the <code>params</code> objects
     *            determines what constructor is called.
     * @return if <code>condition</code> is <code>false</code>,
     *         <code>null</code> is returned. If an {@link Exception}/
     *         {@link Throwable} occurs while instantiating the
     *         {@link Throwable} instance, this {@link Exception}/
     *         {@link Throwable} is returned. Under all other circumstances,
     *         this method will not get the opportunity to return anything, as
     *         it will throw a {@link T}.
     * @throws T
     *             if <code>condition</code> is <code>true</code>.
     */
    public static <T extends Throwable> Throwable throwIfTrue(final boolean condition,
            final Class<T> throwable, final Object... params) throws T {

        if (!condition) {
            return null;
        }
        return throwNow(throwable, params);
    }

    /**
     * @param <T>
     * @param throwable
     * @param params
     * @return
     * @throws T
     */
    public static <T extends Throwable> Throwable throwNow(final Class<T> throwable,
            final Object... params) throws T {

        Throwable errorThrowable = null;
        T throwableToThrow = null;
        try {
            throwableToThrow = create(throwable, params);

        } catch (IllegalArgumentException ex) {
            errorThrowable = ex;
        }

        if (throwableToThrow != null) {
            throw throwableToThrow;
        }

        return errorThrowable;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(final Class<T> throwable, final Object... params) {

        try {
            return (T) ConstructorUtils.invokeConstructor(throwable, params);
        } catch (Exception e) {
            String parameterTypeString = createParameterTypeString(params);
            throw new IllegalArgumentException(format(
                    "Could not instantiate Throwable class [%s] with parameter types [%s]. "
                            + "Error while invoking constructor.", throwable.getName(),
                    parameterTypeString), e);
        }
    }

    private static String createParameterTypeString(final Object[] params) {

        String s = "";
        for (Object param : params) {
            if (param == null) {
                s += "-Unknown-, ";
            } else {
                Class<?> paramType = param.getClass();
                s += paramType.getSimpleName() + ", ";
            }
        }
        s = s.substring(0, s.length() - 2);
        return s;
    }
}
