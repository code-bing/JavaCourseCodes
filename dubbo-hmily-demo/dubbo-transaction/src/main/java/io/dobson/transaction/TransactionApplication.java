package io.dobson.transaction;

import io.dobson.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionApplication implements ApplicationRunner {
    @Autowired
    private TransactionService  transactionService;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(TransactionApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        transactionService.pay();
    }
}
