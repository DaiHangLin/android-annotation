package com.explore.lin.processorlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lin
 * @date 18/6/28
 * @license Copyright (c) 2016 那镁克
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface ClassBindView {
    int value();
}
