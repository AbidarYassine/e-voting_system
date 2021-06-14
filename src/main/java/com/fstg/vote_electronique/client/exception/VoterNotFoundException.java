package com.fstg.vote_electronique.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class VoterNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3212198807560477661L;

    public VoterNotFoundException() {
        super();
    }

    public VoterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VoterNotFoundException(String message) {
        super(message);
    }

    public VoterNotFoundException(Throwable cause) {
        super(cause);
    }
}