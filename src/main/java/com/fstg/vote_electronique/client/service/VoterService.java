package com.fstg.vote_electronique.client.service;


import com.fstg.vote_electronique.shared.beans.Voter;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

public interface VoterService {
    public Voter findById(Long id);

    public Voter saveVoter(Voter voter);
}
