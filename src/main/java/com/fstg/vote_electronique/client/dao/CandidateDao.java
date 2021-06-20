package com.fstg.vote_electronique.client.dao;

import com.fstg.vote_electronique.shared.beans.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository()
public interface CandidateDao extends JpaRepository<Candidate, Long> {
    public Candidate findByName(String name);

}
