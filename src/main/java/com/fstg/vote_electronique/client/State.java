package com.fstg.vote_electronique.client;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class State {
    static HashMap<Long, KeyPair> keyPair_idVoter = new HashMap<>();

    static List<PublicKey> voted_voters = new ArrayList<>();

    public static List<PublicKey> getVoted_voters() {
        return voted_voters;
    }

    public static void addVoterVoted(PublicKey publicKey) {
        voted_voters.add(publicKey);
    }


    public static KeyPair getById(long id) {
        return keyPair_idVoter.get(id);
    }

    public static void addVoter(long id, KeyPair keyPair) {
        keyPair_idVoter.put(id, keyPair);
    }

    public static List<KeyPair> getPairKey() {
        ArrayList<KeyPair> keyList = new ArrayList(keyPair_idVoter.keySet());
        return keyList;
    }
}
