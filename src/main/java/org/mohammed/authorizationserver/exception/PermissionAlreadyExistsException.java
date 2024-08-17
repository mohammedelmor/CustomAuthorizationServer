package org.mohammed.authorizationserver.exception;

public class PermissionAlreadyExistsException extends RuntimeException {

    public PermissionAlreadyExistsException() {
        super();
    }

    public PermissionAlreadyExistsException(String message) {
        super(message);
    }

}
