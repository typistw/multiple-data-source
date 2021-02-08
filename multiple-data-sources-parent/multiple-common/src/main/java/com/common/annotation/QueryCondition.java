package com.common.annotation;

import com.common.vo.page.SearchType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface QueryCondition {

	SearchType searchType()default SearchType.EQ;
	
	String relateInField() default "";//相关字段
}
