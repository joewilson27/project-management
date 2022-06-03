package com.wilson.pma.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;

@Target({ElementType.FIELD}) // this validation can be applied on field, this from annotation @Target
@Retention(RetentionPolicy.RUNTIME) // this retention annotation describes if the customer annotation 
									// should be available in the bytecode
@Constraint(validatedBy=UniqueValidator.class) // will using UniqueValidator
public @interface UniqueValue {
	
	String message() default "Unique Constraint violated";
	
	Class<?>[] groups() default{};
	
	Class<?extends Payload>[] payload() default{}; 
	
}
