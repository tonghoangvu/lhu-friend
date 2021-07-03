package com.tonghoangvu.lhufriend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class LhuFriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(LhuFriendApplication.class, args);
    }
}
