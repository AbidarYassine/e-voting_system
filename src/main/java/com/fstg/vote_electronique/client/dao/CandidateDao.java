package com.fstg.vote_electronique.client.dao;

import com.fstg.vote_electronique.shared.beans.Candidate;
import com.fstg.vote_electronique.shared.beans.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;

@Repository()
public interface CandidateDao extends JpaRepository<Candidate, Long> {


}
