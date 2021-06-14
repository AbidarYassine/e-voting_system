package com.fstg.vote_electronique.client.service.serviceImpl;

import com.fstg.vote_electronique.client.dao.VoterDao;
import com.fstg.vote_electronique.client.service.VoterService;
import com.fstg.vote_electronique.shared.beans.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class VoterServiceImpl implements VoterService {

    @Autowired
    private VoterDao voterDao;


    @Override
    public Voter findById(Long id) {
        Optional<Voter> voter = voterDao.findById(id);
        return voter.orElse(null);
    }

    @Override
    public Voter saveVoter(Voter voter) {
        return voterDao.save(voter);
    }

    @Override
    public Voter findByName(String name) {
        Voter voter = voterDao.findByName(name);
        return voter;
    }

}
