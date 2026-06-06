package br.uniesp.si.iespflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IespflixApplication {

    public static void main(String[] args) {
        SpringApplication.run(IespflixApplication.class, args);
    }
}