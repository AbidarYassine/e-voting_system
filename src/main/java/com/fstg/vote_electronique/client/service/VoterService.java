package com.fstg.vote_electronique.client.service;


import com.fstg.vote_electronique.shared.beans.Voter;

public interface VoterService {
    public Voter findById(Long id);

    public Voter saveVoter(Voter voter);
    public Voter findByName(String name);
}
