package com.accepted.match_api.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DifferentTeamsValidator.class)
public @interface DifferentTeams {
    String message() default "Team A and Team B must be different";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
