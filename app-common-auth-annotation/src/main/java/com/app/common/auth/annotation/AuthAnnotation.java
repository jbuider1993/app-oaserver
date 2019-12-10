package com.app.common.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法权限注解
 * @author 卫志强
 *
 */
@Target({ElementType.METHOD}) // 说明了Annotation所修饰的对象范围； 作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented // 说明该注解将被包含在javadoc中
public @interface AuthAnnotation {
	
	/**
	 * 权限url
	 * @return
	 */
	String value() default "";
	
}
