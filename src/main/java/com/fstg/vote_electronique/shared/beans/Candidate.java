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

@Entity(name = "candidates")
@Data()
@AllArgsConstructor()
@NoArgsConstructor()
@ToString()
public class Candidate implements Serializable {

    @Id()
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Integer count = 0;
}
