package br.edu.iftm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AplicacaoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AplicacaoWebApplication.class, args);
    }
}
