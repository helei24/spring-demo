package com.stone.common.lang;

import static com.stone.common.lang.Validator.instance;

public final class Validators {

    private Validators() {

    }

    public static final Validator<IllegalArgumentException> ia = instance(IllegalArgumentException.class);

    public static final Validator<IllegalStateException> is = instance(IllegalStateException.class);
}
