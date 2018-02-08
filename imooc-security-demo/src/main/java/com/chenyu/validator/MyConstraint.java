package com.chenyu.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 我的约束测试
 *
 * @author
 * @create 2018-02-08 18:23
 **/
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    String message() default "测试数据";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
