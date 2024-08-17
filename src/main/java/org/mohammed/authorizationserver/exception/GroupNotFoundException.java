package org.mohammed.authorizationserver.exception;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException() {
        super();
    }

    public GroupNotFoundException(String message) {
        super(message);
    }

}
