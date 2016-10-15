package com.mlh.expression.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 变量与字段的映射关系
 * @description 
 * @author 作者：MENGLIHAO
 * @create 创建时间：2016年10月15日下午4:43:53
 * @update 修改时间：2016年10月15日下午4:43:53
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Var {
	public String name() default "";
}
