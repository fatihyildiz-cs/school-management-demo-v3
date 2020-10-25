package com.management.schoolmanagementv3.Entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BranchCodeValidator implements ConstraintValidator<BranchCode, String> {
    public void initialize(BranchCode constraint) {
    }

    public boolean isValid(String branchCode, ConstraintValidatorContext context) {

        if(branchCode.length() == 1 && Character.isLetter(branchCode.charAt(0)) && Character.isUpperCase(branchCode.charAt(0))){
            return true;
        }
        return false;
    }
}
