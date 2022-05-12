package com.auth.micro.security;


public final class SecurityConstants {

    public SecurityConstants() {
        throw new UnsupportedOperationException();
    }

    public final static long   EXPIRATION_TIME     = 864000000;
    public final static String SIGN_UP_URL         = "/auth";
    public final static String USERS_RESOURCE      = "/users";
    public final static String SIGN_IN_URL         = "/users";
    public final static String LOGIN_PATH          = SIGN_UP_URL+"/users/login";
    public final static String TOKEN_PREFIX        = "Bearer ";
    public final static String HEADER_AUTHORIZATION = "Authorization";
    public final static String TOKEN_SECRET        = "dfg893hdc475zwerop4tghg4dfeqaas ? = - 01jznm - 9 ";

}
