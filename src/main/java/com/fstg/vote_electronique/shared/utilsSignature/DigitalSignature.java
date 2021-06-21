package com.fstg.vote_electronique.shared.utilsSignature;

import java.security.*;

public class GetKey {


    //    generate KeyPair (public key ,private Key ) par L'algorithm DSA Digital Signature Algorithm
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {

//        The KeyGenerator class is used to generate keys for a given algorithm
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        return pair;
    }

    public static byte[] signMessage(PrivateKey sk, String message)
            throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
//        La classe Java Signature (java.security.Signature) peut créer une signature numérique pour les données binaires.
//        Une signature numérique est un condensé de messages cryptés avec une clé privée d’une paire de clés privées / publiques.
//        Toute personne en possession de la clé publique peut vérifier la signature numérique.
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initSign(sk);
        sig.update(message.getBytes());
        return sig.sign();
    }


    //    ici  vérification la signature numérique par le public key
    public static boolean verify(PublicKey pk, String message, byte[] signatrue)
            throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pk);
        sig.update(message.getBytes());
        return sig.verify(signatrue);
    }

}
