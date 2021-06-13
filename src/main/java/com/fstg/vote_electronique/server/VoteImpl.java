package com.fstg.vote_electronique.server;


import com.fstg.vote_electronique.client.dto.VoteDto;
import com.fstg.vote_electronique.shared.beans.VoteModel;
import com.fstg.vote_electronique.shared.utilsSignature.GetKey;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;

public class VoteImpl extends UnicastRemoteObject implements Vote {
    protected VoteImpl() throws RemoteException {
    }

    @Override
    public byte[] register(long id) throws RemoteException {
        KeyPair key = null;
        try {
            key = GetKey.generateKeyPair();
            State.addVoter(id, key);
            return key.getPublic().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long vote(VoteDto vote) throws RemoteException {
        try {
            KeyPair keyPair = State.getById(vote.getVoter_id());
            byte[] signMessage = GetKey.signMessage(keyPair.getPrivate(), vote.getMessage());
            boolean auth = GetKey.verify(keyPair.getPublic(), vote.getMessage(), signMessage);
            if (!auth) {
                System.err.println("Signature is not correct !!");
                return -1;
            }
            boolean voted = State.getVoted_voters().contains(keyPair.getPublic());
            if (voted) {
                System.err.println("voter with publicKey" + keyPair.getPublic() + " already voted !!");
                return -2;
            } else {
                State.addVoterVoted(keyPair.getPublic());
            }
            return vote.getCandidatId();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
