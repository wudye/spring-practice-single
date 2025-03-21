package com.mwu.myv1.componetConfig.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // can be applied to method
@Retention(RetentionPolicy.RUNTIME) // available at runtime
public @interface TrackExecutionTime {}
