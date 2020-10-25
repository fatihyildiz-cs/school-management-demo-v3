package com.management.schoolmanagementv3.Entity;

public enum SubjectType {

    SCIENCE("Science"),
    SOCIAL("Social"),
    MATH("Mathematics"),
    ENGLISH("English");

    private final String subjectName;

    SubjectType(String subjectName) {
        this.subjectName = subjectName;
    }
}
