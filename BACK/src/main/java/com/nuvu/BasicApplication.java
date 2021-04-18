package com.nuvu;

import com.nuvu.model.CreditCard;
import com.nuvu.model.Person;
import com.nuvu.repositories.CreditCardRepository;
import com.nuvu.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@ComponentScan()
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Bean
    CommandLineRunner initPersonRepository(PersonRepository personRepository) {
        return args -> {
            Person person = new Person(1, "1018458060", "JUAN CUBILLOS", "CUBILLOS2093HOTMAIL.COM", new Date());
            personRepository.save(person);
        };
    }

    @Bean
    CommandLineRunner initCreditCardRepository(CreditCardRepository creditCardRepository) {
        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date validUntil = sdf.parse("01/01/2022");
            CreditCard creditCard = new CreditCard(1, 45526877, validUntil, 209, 1);
            creditCardRepository.save(creditCard);
        };
    }
}
