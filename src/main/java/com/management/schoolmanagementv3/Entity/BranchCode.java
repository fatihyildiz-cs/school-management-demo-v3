package com.management.schoolmanagementv3.Entity;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BranchCodeValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BranchCode {

    String message() default "Invalid branch code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
