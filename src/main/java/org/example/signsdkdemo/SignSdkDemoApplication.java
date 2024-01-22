package org.example.signsdkdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SignSdkDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignSdkDemoApplication.class, args);
    }

}
