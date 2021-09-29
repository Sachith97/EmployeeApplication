package com.sac.EmployeeApp.enums;

public enum Response {

    SUCCESS(1, "Success"),
    FAILED(2, "Failed"),
    INSUFFICIENT_DATA(3, "Insufficient data"),
    UNAVAILABLE_EMPLOYEE(4, "Unavailable employee data"),
    UNAVAILABLE_DEPARTMENT(4, "Unavailable department data");

    private final int responseCode;
    private final String responseMessage;

    Response(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }
}
