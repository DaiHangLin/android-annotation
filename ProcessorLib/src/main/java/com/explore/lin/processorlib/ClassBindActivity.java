package com.explore.lin.processorlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lin
 * @date 18/6/29
 * @license Copyright (c) 2016 那镁克
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ClassBindActivity {
}
