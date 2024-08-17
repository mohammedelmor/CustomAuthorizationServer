package org.mohammed.authorizationserver.exception;

public class GroupAlreadyExistsException extends RuntimeException {

    public GroupAlreadyExistsException() {
        super();
    }

    public GroupAlreadyExistsException(String message) {
        super(message);
    }

}
