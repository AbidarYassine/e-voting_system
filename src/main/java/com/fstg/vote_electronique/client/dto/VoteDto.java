package com.fstg.vote_electronique.client.dto;


import java.io.Serializable;

public class VoteDto implements Serializable {

    private long candidatId;


    private Long voter_id;

    private String message;

    public long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(long candidatId) {
        this.candidatId = candidatId;
    }

    public Long getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(Long voter_id) {
        this.voter_id = voter_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "VoteDto{" +
                "candidatId=" + candidatId +
                ", voter_id=" + voter_id +
                ", message='" + message + '\'' +
                '}';
    }
}
