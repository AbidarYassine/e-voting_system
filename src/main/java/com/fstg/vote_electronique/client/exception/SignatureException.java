package com.fstg.vote_electronique.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class SignatureException extends RuntimeException {
    private static final long serialVersionUID = -3212198807166477661L;

    public SignatureException() {
        super();
    }


    public SignatureException(String message) {
        super(message);
    }


}
