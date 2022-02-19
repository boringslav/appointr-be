package com.appointr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 * TODO: remove exclude = {DataSourceAutoConfiguration.class}
 *  after connecting to db
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppointrApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointrApplication.class, args);
    }

}
