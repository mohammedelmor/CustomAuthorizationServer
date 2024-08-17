package org.mohammed.authorizationserver.exception;

public class PermissionNotFoundException extends RuntimeException {

    public PermissionNotFoundException() {
        super();
    }

    public PermissionNotFoundException(String message) {
        super(message);
    }

}
