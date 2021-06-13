package com.fstg.vote_electronique.shared.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.security.PublicKey;

@Entity(name = "voters")
@Data
@AllArgsConstructor()
@NoArgsConstructor()
@ToString()
public class Voter implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "publicKey", unique = true, length = 1024)
    private String publicKey;

    @Column(name = "voted")
    private boolean voted = false;

    @Column(name = "name", unique = true)
    private String name;


    public Voter(String name) {
        this.name = name;
    }

    public Voter(String name, String publiKey) {
        this.name = name;
        this.publicKey = publiKey;
    }
}
