package com.fstg.vote_electronique.server;


import com.fstg.vote_electronique.client.State;
import com.fstg.vote_electronique.client.dto.VoteDto;
import com.fstg.vote_electronique.shared.utilsSignature.GetKey;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;

public class VoteImpl extends UnicastRemoteObject implements Vote {
    protected VoteImpl() throws RemoteException {
    }

    @Override
    public long vote(VoteDto voteDto, PublicKey publicKey) {
        boolean auth = false;
        try {
            auth = GetKey.verify(publicKey, voteDto.getMessage(), voteDto.getSignature());
            if (!auth) {
                System.err.println("Signature is not correct !!");
                return -1;
            }
            boolean voted = State.getVoted_voters().contains(publicKey);
            if (voted) {
                System.err.println("voter with publicKey" + publicKey + " already voted !!");
                return -2;
            } else {
                State.addVoterVoted(publicKey);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return voteDto.getCandidatId();
    }
}
