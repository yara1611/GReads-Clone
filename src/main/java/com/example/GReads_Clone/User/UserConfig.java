package com.example.GReads_Clone.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args->{
           User user1 = new User(
                    "yara", "yara16","1611"
            );
            repository.save(user1);
        };

    }

}
