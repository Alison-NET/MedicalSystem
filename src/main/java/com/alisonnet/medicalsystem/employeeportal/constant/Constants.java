package com.alisonnet.medicalsystem.employeeportal.constant;

public final class Constants {
    public final static String URL_EMPLOYEE_PORTAL = "/employee-portal";

    //  PATTERNS
    public final static String ZIP_PATTERN = "(^[0-9]{5}(?:-[0-9]{4})?$)";
//    public final static String PHONE_PATTERN = "(^$|[0-9]{10})";
//    public final static String PHONE_PATTERN = "(^(\\([0-9]{3}\\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}$)";
    public final static String PHONE_PATTERN = "(^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$)";
    public final static String SSN_PATTERN = "(^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$)";

    public final static String FAX_PATTERN = "(^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$)"; // SAME AS PHONE PATTERN
    public final static String DIRECT_LINE_PATTERN = "(^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$)"; // SAME AS PHONE PATTERN
    public final static String NPI_PATTERN = "(^[0-9]{10}$)";

    //  =========


    //  VALIDATION MESSAGES
    public final static String VALIDATION_MSG_CANT_BE_EMPTY = "Can't be empty";

    public final static String VALIDATION_MSG_INVALID_PHONE = "Invalid phone number";
    public final static String VALIDATION_MSG_INVALID_FAX = "Invalid fax number";
    public final static String VALIDATION_MSG_INVALID_DIRECT_LINE = "Invalid direct line number";
    public final static String VALIDATION_MSG_INVALID_SNN = "Invalid SSN";
    public final static String VALIDATION_MSG_INVALID_ZIP = "Invalid ZIP Code";
    public final static String VALIDATION_MSG_EMAIL_USED = "This email is already in use";
    //  ==========

    //  MESSAGES
    public final static String BASIC_EMPLOYEE_THANKS_FOR_REG_MSG = "Thank you for registration. You will receive the message after approval";

    //  EXCEPTIONS MESSAGES

    public final static String DOWNLOAD_DOCUMENT_ACCESS_DENIED_MSG = "You can not download this document";
    public final static String DELETE_DOCUMENT_ACCESS_DENIED_MSG = "You can not delete this document";
    public final static String VIEW_PAGE_ACCESS_DENIED_MSG = "You are not allowed ещ view this page";


    public final static String INVALID_EMPLOYEE_ID_MSG = "There isn't such an employee";
    public final static String ALREADY_REGISTERED_EMPLOYEE_MSG = "Employee with such id is already registered";
    public final static String INVALID_DOCUMENT_ID_MSG = "There isn't such a document";
    public final static String ACTIVE_EMPLOYEE_ABSENCE_MSG = "There is no employee logged in";

    //  FORMATS
    public final static String WORK_TIME_FORMAT = "hh:mm a";
    //  ==========

    //  NUMBERS
    public final static int MAX_PROVIDERS_PER_ACCOUNT = 5;
    public final static int MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT = 2;
    //  ==========

    //  DEPARTMENTS
    public static String SYSTEM_ADMIN_DEPARTMENT = "System Admin";
    public static String HUMAN_RESOURCES_DEPARTMENT = "Human Resources";
    //  ==========
}