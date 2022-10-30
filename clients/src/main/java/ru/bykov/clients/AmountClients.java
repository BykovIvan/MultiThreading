package ru.bykov.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.bykov.clients.client.AmountClient;
import ru.bykov.clients.service.AmountService;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@SpringBootApplication
public class AmountClients {

    public static void main(String[] args) {
        SpringApplication.run(AmountClients.class, args);
    }


}
