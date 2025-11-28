package com.example.GReads_Clone.User;

import com.example.GReads_Clone.ReadingLists.ReadingList;
import com.example.GReads_Clone.ReadingLists.ReadingListRepository;
import com.example.GReads_Clone.enums.ReadingListType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository, ReadingListRepository readingNowRepository){
        return args->{
           User user1 = new User(
                    "yara", "yara16","1611"
            );
            repository.save(user1);
            ReadingList readingNow = new ReadingList();
            readingNow.setUser(user1);
            readingNow.setListType(ReadingListType.READING_NOW);
            readingNowRepository.save(readingNow);
        };

    }

}
