package org.mohammed.authorizationserver.exception;

public class UserPermissionNotFoundException extends RuntimeException {

    public UserPermissionNotFoundException() {
        super();
    }

    public UserPermissionNotFoundException(String message) {
        super(message);
    }

}
