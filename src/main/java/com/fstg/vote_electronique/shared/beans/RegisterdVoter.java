package com.fstg.vote_electronique.shared.beans;

import java.io.Serializable;
import java.security.KeyPair;

public class RegisterdVoter implements Serializable {


    public KeyPair keyPair;

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }
}
