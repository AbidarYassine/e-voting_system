package com.fstg.vote_electronique.client;


import com.fstg.vote_electronique.server.Vote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


// design patterne singleton
public class GetRemoteService {
    private static final GetRemoteService instance = new GetRemoteService();

    static String url = "IRISI_VOTE_SYSTEM";

    private GetRemoteService() {
    }

    public static GetRemoteService getInstance() {
        return instance;
    }

    public Vote getService() {

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            // objet server (l'objet stub)
            return (Vote) registry.lookup(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
