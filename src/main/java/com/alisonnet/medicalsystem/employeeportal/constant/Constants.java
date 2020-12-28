package com.alisonnet.medicalsystem.employeeportal.constant;

public final class Constants {
    public final static String URL_EMPLOYEE_PORTAL = "/employee-portal";

    //  PATTERNS
    public final static String ZIP_PATTERN = "(^[0-9]{5}(?:-[0-9]{4})?$)";
//    public final static String PHONE_PATTERN = "(^$|[0-9]{10})";
    public final static String PHONE_PATTERN = "(^(\\([0-9]{3}\\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}$)";
    public final static String SSN_PATTERN = "(^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$)";

    //  =========


    //  VALIDATION MESSAGES
    public final static String VALIDATION_MSG_CANT_BE_EMPTY = "Can't be empty";

    public final static String VALIDATION_MSG_INVALID_PHONE = "Invalid phone number";
    public final static String VALIDATION_MSG_INVALID_SNN = "Invalid SSN";
    public final static String VALIDATION_MSG_INVALID_ZIP = "Invalid ZIP Code";
    //  ==========

    public final static String BASIC_EMPLOYEE_THANK_FOR_REG = "Thank you for registration. You will receive the message after approval";

    //  FORMATS
    public final static String WORK_TIME_FORMAT = "hh:mm a";
    //  ==========

    //  NUMBERS
    public final static int MAX_PROVIDERS_PER_ACCOUNT = 5;
    public final static int MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT = 2;
    //  ==========

    //  DEPARTMENTS
    public static String system_admin_department = "System Admin";
    public static String human_resources_department = "Human Resources";
    //  ==========
}