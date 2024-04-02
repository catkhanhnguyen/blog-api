package com.blog.demo.utils;

public class Constants {
    private Constants() {}
    public static final long REFRESH_TOKEN_EXPIRATION_MINUTES = 300;
    public static final long ACCESS_TOKEN_EXPIRATION_MINUTES = 10;

    public static final String MESSAGE_INVALID_ACCESS_TOKEN = "access token is invalid.";
    public static final String MESSAGE_INVALID_REFRESH_TOKEN = "request token is invalid.";

    public static final String MESSAGE_INVALID_USER_NAME = "user name is invalid.";
    public static final String MESSAGE_INVALID_PASSWORD = "password is invalid.";
    public static final String MESSAGE_INVALID_ROLES = "roles is invalid.";

    public static final String MESSAGE_SAME_USER_NAME_EXIST = "user with the same user name exists.";

    public static final String MESSAGE_BAD_CREDENTIALS = "bad credentials.";



}
