package com.fstg.vote_electronique.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class ResultReponse {

    private String name;
    private Integer count;

    public ResultReponse(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public ResultReponse() {
    }
}
