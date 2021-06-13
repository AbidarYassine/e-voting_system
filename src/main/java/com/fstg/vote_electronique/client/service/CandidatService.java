package com.fstg.vote_electronique.client.service;

import com.fstg.vote_electronique.shared.beans.Candidate;
import com.fstg.vote_electronique.shared.beans.Voter;

import java.util.List;

public interface CandidatService {

    public List<Candidate> getAllCandidates();

    public Candidate addCandidat(Candidate candidate);

    public Candidate findById(Long id);
}
