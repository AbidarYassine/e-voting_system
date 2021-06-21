package com.fstg.vote_electronique.client.controllers;

import com.fstg.vote_electronique.client.GetRemoteService;
import com.fstg.vote_electronique.client.State;
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
import com.fstg.vote_electronique.shared.utilsSignature.DigitalSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.RemoteException;
import java.security.*;
import java.util.List;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    private final GetRemoteService getRemoteService = GetRemoteService.getInstance();
    private final Vote remote_service = getRemoteService.getService();

    @Autowired
    private VoterService voterService;
    @Autowired
    private CandidatService candidatService;


    @PostMapping("/voter/register")
    public Voter register(@RequestBody() VoterDto voterDto) {
        Voter voter = null;
        KeyPair key = null;
        try {
            voter = new Voter(voterDto.getName());
            Voter voterF = voterService.findByName(voterDto.getName());
            if (voterF != null)
                throw new VoterAlreadyExiste("Voter with name " + voterDto.getName() + " already exists");
            voter = voterService.saveVoter(voter);
            key = DigitalSignature.generateKeyPair();
            State.addVoter(voter.getId(), key);
            voter.setPublicKey(key.getPublic().toString());
            voterService.saveVoter(voter);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
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

    // le principe de cette méthode est génère une signature à partir du private key de voter // et de l'envoyer.
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
            KeyPair keyPair = State.getById(voteDto.getVoter_id());
//            génèrer le sign Message
            byte[] signMessage = DigitalSignature.signMessage(keyPair.getPrivate(), voteDto.getMessage());
            voteDto.setSignature(signMessage);
            long id = remote_service.vote(voteDto, keyPair.getPublic());


//            gerer les exception possible
            System.out.println("Id du Candidate est " + id);
            if (id == -2) {
                System.err.println("Voter with id " + voteDto.getVoter_id() + " already voted");
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Voter with id " + voteDto.getVoter_id() + "already voted");
            }
            if (id == -1) {
                System.err.println("Signature is not correct !!");
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Signature is not correct !!");
            }
            candidate = candidatService.findById(id);
            Integer count = candidate.getCount() + 1;
            candidate.setCount(count);
            candidatService.addCandidat(candidate);
            return candidate;
        } catch (RemoteException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchProviderException | SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    vote result
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
