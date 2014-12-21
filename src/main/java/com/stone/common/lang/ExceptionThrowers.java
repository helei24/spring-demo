package com.stone.common.lang;

import static com.stone.common.lang.ExceptionThrower.instance;

public final class ExceptionThrowers {

    private ExceptionThrowers() {

    }

    /**
     * {@link ExceptionThrower} for {@link IllegalStateException}s. Use this
     * object to conditionally throw {@link IllegalStateException}s.
     */
    public static final ExceptionThrower<IllegalStateException> ise = instance(IllegalStateException.class);

    /**
     * {@link ExceptionThrower} for {@link RuntimeException}s. Use this object
     * to conditionally throw {@link RuntimeException}s.
     */
    public static final ExceptionThrower<RuntimeException> rte = instance(RuntimeException.class);

    /**
     * {@link ExceptionThrower} for {@link IllegalArgumentException}s. Use this
     * object to conditionally throw {@link IllegalArgumentException}s.
     */
    public static final ExceptionThrower<IllegalArgumentException> iae = instance(IllegalArgumentException.class);

    /**
     * {@link ExceptionThrower} for {@link NullPointerException}s. Use this
     * object to conditionally throw {@link NullPointerException}s.
     */
    public static final ExceptionThrower<NullPointerException> npe = instance(NullPointerException.class);

    /**
     * {@link ExceptionThrower} for generic {@link Exception}s. Use this object
     * to conditionally throw {@link Exception}s. You should rarely use this;
     * define your own {@link Exception} subclass or use an existing, more
     * specific {@link Exception} subclass.
     */
    public static final ExceptionThrower<Exception> e = instance(Exception.class);
}
