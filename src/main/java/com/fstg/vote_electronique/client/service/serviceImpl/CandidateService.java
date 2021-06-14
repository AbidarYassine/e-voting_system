package com.fstg.vote_electronique.client.service.serviceImpl;

import com.fstg.vote_electronique.client.dao.CandidateDao;
import com.fstg.vote_electronique.client.service.CandidatService;
import com.fstg.vote_electronique.shared.beans.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service()
public class CandidateService implements CandidatService {
    @Autowired
    private CandidateDao candidateDao;

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateDao.findAll();
    }

    @Override
    public Candidate addCandidat(Candidate candidate) {
        return candidateDao.save(candidate);
    }

    @Override
    public Candidate findById(Long id) {
        Optional<Candidate> candidate = candidateDao.findById(id);
        return candidate.orElse(null);
    }

    @Override
    public Candidate findByName(String name) {
        return candidateDao.findByName(name);
    }
}
