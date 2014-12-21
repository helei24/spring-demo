package com.stone.commons.core.assertion;

public final class Assert {

    private Assert() {

    }

    /**
     * Assert that <i>object</i> is not null.
     * 
     * @param object
     *            The object to assert.
     * @param objectName
     *            A name of the object. Usually the name of the variable. Used
     *            to create a more descriptive NPE.
     * @throws NullPointerException
     *             Thrown in case <i>object</i> is null.
     */
    public static void notNull(final Object object, final String objectName) {

        if (object == null) {
            throw new NullPointerException(objectName + " is null.");
        }
    }

    /**
     * Assert that <i>expression</i> is true.
     * 
     * @param expression
     *            Expression to assert.
     * @param expressionName
     *            A descriptive name for the expression. If a boolean variable,
     *            use it's variable name.
     */
    public static void isTrue(final boolean expression, final String expressionName) {

        if (!expression) {
            throw new AssertionException("true expected for " + expressionName);
        }
    }
}
