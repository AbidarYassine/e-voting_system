package com.fstg.vote_electronique.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CandidateNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3212198807566477661L;

    public CandidateNotFoundException() {
        super();
    }

    public CandidateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CandidateNotFoundException(String message) {
        super(message);
    }

    public CandidateNotFoundException(Throwable cause) {
        super(cause);
    }
}
