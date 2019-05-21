/**
 * 
 */
package com.imooc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author zhailiang
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD}) //此自定义注解课标注在方法和字段上
@Retention(RetentionPolicy.RUNTIME)//表示是运行时的一个注解
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
