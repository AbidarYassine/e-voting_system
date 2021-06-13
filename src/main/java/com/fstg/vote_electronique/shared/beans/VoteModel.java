package com.fstg.vote_electronique.shared.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.security.PublicKey;

@Data()
@AllArgsConstructor()
@NoArgsConstructor()
@ToString()
public class VoteModel implements Serializable {


    @Id()
    @GeneratedValue
    private Long id;

    @Column(name = "candidatId")
    private long candidatId;

    @Column(name = "vote_id")
    private Long voter_id;

    @Column()
    private String message;
}
