package com.fstg.vote_electronique.server;


import com.fstg.vote_electronique.client.dto.VoteDto;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Vote extends Remote {


    byte[] register(long id) throws RemoteException;

    long vote(VoteDto vote) throws RemoteException;


//    long isVoted(byte[] publicKey) throws RemoteException;
}
