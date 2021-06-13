package com.fstg.vote_electronique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class VoteElectroniqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteElectroniqueApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner demo(VoterDao voterDao) {
//        return (args) -> {
//            Voter v = new Voter();
//            v.setVoted(false);
//            v.setId(3l);
//            v.setPrivateKey("scvhaca45");
//            v.setPrivateKey("*sdsd/s*sds8");
//            Voter voterRunder = voterDao.save(v);
//            System.out.println(voterRunder);
//            Optional<Voter> vo = voterDao.findById(1l);
//            System.out.println("voter is" + vo.get().toString());
//
//        };
//    }

}
