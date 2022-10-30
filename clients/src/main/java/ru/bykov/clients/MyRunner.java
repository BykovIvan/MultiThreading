package ru.bykov.clients;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.bykov.clients.service.AmountService;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
    private AmountService amountService;

    public MyRunner(AmountService amountService) {
        this.amountService = amountService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue){
            System.out.println("Enter command start!");
            if (scanner.nextLine().equals("start")) {
                amountService.start();
            } else if (scanner.nextLine().equals("exit")){
                isTrue = false;
            }
        }

    }
}
