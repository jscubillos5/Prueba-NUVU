package com.dao;

import com.model.CreditCard;
import com.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

@Component
public class CreditCardDao {

    @Autowired
    CreditCardRepository creditCardRepository;

    public CreditCard getCreditCard(Long id) {
        return creditCardRepository.findById(id).orElse(null);
    }

    public CreditCard getCreditCardByNumber(int number) {
        return creditCardRepository.findByNumber(number);
    }

    public List<CreditCard> getCreditCardByPerson(Long idPerson) {
        List<CreditCard> results = new ArrayList<>();
        for (CreditCard creditCard : creditCardRepository.findAll()) {
            if (creditCard.getIdPerson() == idPerson) {
                results.add(creditCard);
            }
        }
        return results;
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }

    public void deleteCreditCard(Long id) {
        creditCardRepository.deleteById(id);
    }
}
