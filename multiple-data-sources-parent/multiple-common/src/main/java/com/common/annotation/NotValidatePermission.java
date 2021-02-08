package com.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不校验安全
 * @author Administrator
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface NotValidatePermission {

}
