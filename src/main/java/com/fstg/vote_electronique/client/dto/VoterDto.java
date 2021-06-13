package com.fstg.vote_electronique.client.dto;

import java.io.Serializable;

public class VoterDto implements Serializable {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
