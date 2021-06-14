package com.fstg.vote_electronique.client.controllers;

import com.fstg.vote_electronique.client.GetRemoteService;
import com.fstg.vote_electronique.client.dto.VoteDto;
import com.fstg.vote_electronique.client.dto.VoterDto;
import com.fstg.vote_electronique.client.exception.CandidateAlreadyExist;
import com.fstg.vote_electronique.client.exception.CandidateNotFoundException;
import com.fstg.vote_electronique.client.exception.VoterAlreadyExiste;
import com.fstg.vote_electronique.client.exception.VoterNotFoundException;
import com.fstg.vote_electronique.client.response.ResultReponse;
import com.fstg.vote_electronique.client.service.CandidatService;
import com.fstg.vote_electronique.client.service.VoterService;
import com.fstg.vote_electronique.server.Vote;
import com.fstg.vote_electronique.shared.beans.Candidate;
import com.fstg.vote_electronique.shared.beans.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.RemoteException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

@RestController()
public class HomeController {

    private final GetRemoteService getRemoteService = GetRemoteService.getInstance();
    private final Vote remote_service = getRemoteService.getService();

    @Autowired
    private VoterService voterService;
    @Autowired
    private CandidatService candidatService;


    @PostMapping("/voter/register")
    public Voter home(@RequestBody() VoterDto voterDto) {
        Voter voter = null;
        byte[] bytes = null;
        try {
            voter = new Voter(voterDto.getName());
            Voter voterF = voterService.findByName(voterDto.getName());
            if (voterF != null)
                throw new VoterAlreadyExiste("Voter with name " + voterDto.getName() + " already exists");
            voter = voterService.saveVoter(voter);
            bytes = remote_service.register(voter.getId());
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytes);
            PublicKey pk = keyFactory.generatePublic(publicKeySpec);
            voter.setPublicKey(pk.toString());
            voterService.saveVoter(voter);
        } catch (RemoteException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return voter;
    }


    @GetMapping("/candidates")
    public List<Candidate> getAllCandidates() {
        try {
            return candidatService.getAllCandidates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/candidates")
    public Candidate addCandidat(@RequestBody() Candidate candidate) {

        Candidate candiF = candidatService.findByName(candidate.getName());
        if (candiF != null)
            throw new CandidateAlreadyExist("Candidate with name " + candidate.getName() + " already exists");
        return candidatService.addCandidat(candidate);
    }

    //
    @PostMapping("/vote")
    public Candidate vote(@RequestBody() VoteDto voteDto) {
        try {
            Candidate candidate = null;
            if (voterService.findById(voteDto.getVoter_id()) == null) {
                throw new VoterNotFoundException("Voter not found");
            }
            if (candidatService.findById(voteDto.getCandidatId()) == null) {
                throw new CandidateNotFoundException("Candidate not found");
            }
            long id = remote_service.vote(voteDto);
            System.out.println("Id du Candidate est " + id);
            if (id == -2) {
                System.err.println("Voter with id " + voteDto.getVoter_id() + " already voted");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voter with id " + voteDto.getVoter_id() + "already voted");
            }
            if (id == -1) {
                System.err.println("Signature is not correct !!");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Signature is not correct !!");
            }
            candidate = candidatService.findById(id);
            Integer count = candidate.getCount() + 1;
            candidate.setCount(count);
            candidatService.addCandidat(candidate);
            return candidate;
        } catch (RemoteException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping("/vote-result")
    public ResponseEntity<ResultReponse> vote() {
        List<Candidate> candidates = candidatService.getAllCandidates();
        if (candidates.size() > 0) {
            Candidate gan = candidates.get(0);
            for (Candidate c : candidates) {
                if (c.getCount() > gan.getCount()) gan = c;
            }
            ResultReponse resultReponse = new ResultReponse(gan.getName(), gan.getCount());
            return new ResponseEntity<ResultReponse>(resultReponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<ResultReponse>(new ResultReponse(), HttpStatus.OK);
        }
    }
}