package com.fstg.vote_electronique.client.exception;


public class CandidateAlreadyExist extends RuntimeException {
    private static final long serialVersionUID = -3212198807566477661L;

    public CandidateAlreadyExist() {
        super();
    }

    public CandidateAlreadyExist(String message) {
        super(message);
    }
}
