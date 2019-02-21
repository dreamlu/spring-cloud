package com.kedacom.keda.annotation;

/**
 * 实习考核项目
 * com.kedacom.apigateway.authorization.annotation
 * 2018-01-18-13:49
 * 2018
 *  on 2018-01-18.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
