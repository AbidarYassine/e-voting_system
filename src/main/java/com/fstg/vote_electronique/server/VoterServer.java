package com.fstg.vote_electronique.server;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class VoterServer {
    public static void main(String[] args) {

        try {
            Vote vote = new VoteImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("IRISI_VOTE_SYSTEM", vote);
            System.out.println("Server started");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
