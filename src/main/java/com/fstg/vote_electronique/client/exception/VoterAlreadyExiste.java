package com.fstg.vote_electronique.client.exception;

public class VoterAlreadyExiste extends RuntimeException {
    private static final long serialVersionUID = -3212198807566477611L;
    public VoterAlreadyExiste() {
        super();
    }

    public VoterAlreadyExiste(String message) {
        super(message);
    }
}
