package com.stone.common.lang;

import static com.stone.common.lang.ErrorMessages.geMessage;
import static com.stone.common.lang.ErrorMessages.gtMessage;
import static com.stone.common.lang.ErrorMessages.leMessage;
import static com.stone.common.lang.ErrorMessages.ltMessage;
import static com.stone.common.lang.ErrorMessages.notEmptyMessage;
import static com.stone.common.lang.ErrorMessages.notNullMessage;
import static java.lang.String.format;

import java.util.Collection;
import java.util.Map;

public class Validator<T extends Throwable> {

    public interface ValidationOperation {

        void validate() throws Exception;
    }

    public static <T extends Throwable> Validator<T> instance(final Class<T> type) {

        return new Validator<T>(type);
    }

    private final ExceptionThrower<T> et;

    public Validator(final Class<T> type) {

        this.et = ExceptionThrower.instance(type);
    }

    public void validateS(final ValidationOperation validationOperation, final String message,
            final Object... messageParams) throws T {

        boolean threwException = false;
        try {
            validationOperation.validate();
        } catch (Exception e) {
            threwException = true;
        }
        isFalse(threwException, message, messageParams);
    }

    /**
     * Validates that <code>condition</code> is <code>true</code>
     * 
     * @param condition
     *            the condition that must be true
     * @param message
     *            the error message to use if the validation fails
     * @param stringParams
     *            the parameters to use when calling String.format on
     *            <code>message</code>
     * @throws T
     *             if the validation fails
     */
    public void isTrue(final boolean condition, final String message, final Object... stringParams)
            throws T {

        et.throwIfFalse(condition, format(message, stringParams));
    }

    /**
     * Validates that <code>condition</code> is <code>false</code>
     * 
     * @param condition
     *            the condition that must be false
     * @param message
     *            the error message to use if the validation fails
     * @param stringParams
     *            the parameters to use when calling String.format on
     *            <code>message</code>
     * @throws T
     *             if the validation fails
     */
    public void isFalse(final boolean condition, final String message, final Object... stringParams)
            throws T {

        et.throwIfTrue(condition, String.format(message, stringParams));
    }

    /**
     * Validates that <code>object</code> is not <code>null</code>
     * 
     * @param object
     *            the object that cannot be null
     * @param message
     *            the error message to use if the validation fails
     * @param stringParams
     *            the parameters to use when calling String.format on
     *            <code>message</code>
     * @throws T
     *             if the validation fails
     */
    public void notNull(final Object object, final String message, final Object... stringParams)
            throws T {

        et.throwIfNull(object, format(message, stringParams));
    }

    /**
     * Validates that <code>object</code> is not <code>null</code>. A default
     * error message will be used.
     * 
     * @param object
     *            the object that cannot be null
     * @throws T
     *             if the validation fails
     */
    public void notNull(final Object object) throws T {

        notNull(object, notNullMessage());
    }

    /**
     * Validates that <code>object</code> is not <code>null</code>
     * 
     * @param object
     *            the object that cannot be null
     * @param objectType
     *            the type of the object that cannot be null (used to generate
     *            error message)
     * @throws T
     *             if the validation fails
     */
    public void notNull(final Object object, final Class<?> objectType) throws T {

        et.throwIfNull(object, notNullMessage(objectType));
    }

    /**
     * Validates that <code>string</code> is not empty (not null, zero length or
     * only white spaces).
     * 
     * @param string
     *            the string that cannot be empty
     * @param name
     *            the name of the variable that the string represents. Used to
     *            generate error message.
     * @throws T
     *             if the validation fails
     */
    public void notEmpty(final String string, final String name) throws T {

        et.throwIfEmpty(string, notEmptyMessage(name));
    }

    /**
     * Validates that <code>collection</code> is not empty
     * 
     * @param collection
     *            the collection that cannot be empty
     * @param name
     *            the name of the variable that the collection represents. Used
     *            to generate error message.
     * @throws T
     *             if the validation fails
     */
    public void notEmpty(final Collection<?> collection, final String name) throws T {

        et.throwIfEmpty(collection, notEmptyMessage(name));
    }

    /**
     * Validates that <code>map</code> is not empty
     * 
     * @param map
     *            the map that cannot be empty
     * @param name
     *            the name of the variable that the map represents. Used to
     *            generate error message.
     * @throws T
     *             if the validation fails
     */
    public void notEmpty(final Map<?, ?> map, final String name) throws T {

        et.throwIfTrue(map.isEmpty(), notEmptyMessage(name));
    }

    /**
     * Validates that none of the <code>objects</code> are null.
     * 
     * @param message
     *            the error message to use if one of the objects are null
     * @param objects
     *            the objects that cannot be null
     * @throws T
     *             if the validation fails
     */
    public void noneNull(final String message, final Object... objects) throws T {

        for (int i = 0; i < objects.length; i++) {
            et.throwIfNullS(objects[i], "%s (cause: object with index [%d] was null)", message, i);
        }
    }

    /**
     * Validates that <code>value</code> is greater than <code>lowerLimit</code>
     * .
     * 
     * @param lowerLimit
     *            the lower limit of the value
     * @param value
     *            the value to compare
     * @param name
     *            the name of the value used in the error message of the thrown
     *            exception
     * @throws T
     *             if the validation fails
     */
    public void gt(final int lowerLimit, final int value, final String name) throws T {

        et.throwIfFalse(value > lowerLimit, gtMessage(name, value, lowerLimit));
    }

    /**
     * Validates that <code>value</code> is greater than or equal to
     * <code>lowerLimit</code>.
     * 
     * @param lowerLimit
     *            the lower limit of the value
     * @param value
     *            the value to compare
     * @param name
     *            the name of the value used in the error message of the thrown
     *            exception
     * @throws T
     *             if the validation fails
     */
    public void ge(final int lowerLimit, final int value, final String name) throws T {

        et.throwIfFalse(value >= lowerLimit, geMessage(name, value, lowerLimit));
    }

    /**
     * Validates that <code>value</code> is less than <code>upperLimit</code>.
     * 
     * @param upperLimit
     *            the upper limit of the value
     * @param value
     *            the value to compare
     * @param name
     *            the name of the value used in the error message of the thrown
     *            exception
     * @throws T
     *             if the validation fails
     */
    public void lt(final int upperLimit, final int value, final String name) throws T {

        et.throwIfFalse(value < upperLimit, ltMessage(name, value, upperLimit));
    }

    /**
     * Validates that <code>value</code> is less than or equal to
     * <code>upperLimit</code>.
     * 
     * @param upperLimit
     *            the upper limit of the value
     * @param value
     *            the value to compare
     * @param name
     *            the name of the value used in the error message of the thrown
     *            exception
     * @throws T
     *             if the validation fails
     */
    public void le(final int upperLimit, final int value, final String name) throws T {

        et.throwIfFalse(value <= upperLimit, leMessage(name, value, upperLimit));
    }

    /**
     * Validates that <code>value</code> is greater than or equal to
     * <code>lowerLimit</code> and less than or equal to <code>upperLimit</code>
     * .
     * 
     * @param lowerLimit
     *            the lower limit of the value
     * @param upperLimit
     *            the upper limit of the value
     * @param value
     *            the value to compare
     * @param name
     *            the name of the value used in the error message of the thrown
     *            exception
     * @throws T
     *             if the validation fails
     */
    public void gele(final int lowerLimit, final int upperLimit, final int value, final String name)
            throws T {

        ge(lowerLimit, value, name);
        le(upperLimit, value, name);
    }

    public void isAssignableFrom(final Object object, final Class<?> type) throws T {

        noneNull("No parameters can be null", object, type);
        boolean isAssignable = type.isAssignableFrom(object.getClass());
        isTrue(isAssignable, "%s is not assigable from %s", type.getName(), object.getClass()
                .getName());
    }
}
