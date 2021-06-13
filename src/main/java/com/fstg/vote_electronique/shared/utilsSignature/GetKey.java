package com.fstg.vote_electronique.shared.utilsSignature;

import java.security.*;

public class GetKey {
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        return pair;
    }

    public static byte[] signMessage(PrivateKey sk, String message)
            throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initSign(sk);
        sig.update(message.getBytes());
        return sig.sign();
    }

    public static boolean verify(PublicKey pk, String message, byte[] signatrue)
            throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pk);
        sig.update(message.getBytes());
        return sig.verify(signatrue);
    }

}
