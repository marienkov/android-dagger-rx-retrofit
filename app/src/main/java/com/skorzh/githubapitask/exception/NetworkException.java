package com.skorzh.githubapitask.exception;

public class NetworkException extends RuntimeException {

    private int code;
    private String message;

    public NetworkException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
