package com.service;

import com.dao.CreditCardDao;
import com.dto.CreditCardDto;
import com.model.CreditCard;
import com.util.LogBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

@Service
public class CreditCardService {

    private static final Integer ERRORTYPEMESSAGE = 2;
    private static final Integer ERRORTYPEINFO = 1;

    @Autowired
    CreditCardDao creditCardDao;
    @Autowired
    LogBack logBack;

    public HttpEntity<List<CreditCard>> getCreditCardByPerson(Long idPerson) {
        HttpStatus httpStatus = HttpStatus.OK;
        List<CreditCard> creditCards = new ArrayList<>();
        try {
            creditCards = creditCardDao.getCreditCardByPerson(idPerson);
            logBack.addLog(ERRORTYPEINFO, CreditCardService.class.getName(), "getCreditCardByPerson", MessageFormat.format("Successful credit cards were listed: {0}", creditCards));
        } catch (Exception ex) {
            httpStatus = HttpStatus.CONFLICT;
            logBack.addLog(ERRORTYPEMESSAGE, CreditCardService.class.getName(), "getCreditCardByPerson", MessageFormat.format("Attention an error occurred when listing the credit cards, the cause is: {0} Cause: {1} StackTrace: {2} Data: {3}", ex.getMessage(), ex.getCause(), ex.getStackTrace(), creditCards));
        }
        return new ResponseEntity<>(creditCards, httpStatus);
    }

    public HttpEntity<CreditCard> addCreditCard(CreditCardDto creditCardDto) {
        final String nameMethod = "addCreditCard";
        HttpStatus httpStatus = HttpStatus.OK;
        CreditCard creditCard = new CreditCard();
        try {
            creditCard.setId(creditCardDto.getId());
            creditCard.setNumber(creditCardDto.getNumber());
            creditCard.setValidUntil(creditCardDto.getValidUntil());
            creditCard.setCvc(creditCardDto.getCvc());
            creditCard.setIdPerson(creditCardDto.getIdPerson());
            if (creditCardDao.getCreditCardByNumber(creditCard.getNumber()) != null) {
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                logBack.addLog(ERRORTYPEMESSAGE, PersonService.class.getName(), nameMethod, MessageFormat.format("Attention already registered credit card, Data: {0}", creditCard));
            } else {
                creditCardDao.addCreditCard(creditCard);
                logBack.addLog(ERRORTYPEINFO, CreditCardService.class.getName(), nameMethod, MessageFormat.format("The credit card was registered successfully: {0}", creditCard));
            }

        } catch (Exception ex) {
            httpStatus = HttpStatus.CONFLICT;
            logBack.addLog(ERRORTYPEMESSAGE, CreditCardService.class.getName(), nameMethod, MessageFormat.format("Attention an error occurred when registering a credit card, the cause is: {0} Cause: {1} StackTrace: {2} Data: {3}", ex.getMessage(), ex.getCause(), ex.getStackTrace(), creditCard));
        }
        return new ResponseEntity<>(creditCard, httpStatus);
    }

    public HttpEntity<Long> deleteCreditCard(long id) {
        final String nameMethod = "deleteCreditCard";
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (creditCardDao.getCreditCard(id) != null) {
                creditCardDao.deleteCreditCard(id);
                logBack.addLog(ERRORTYPEINFO, CreditCardService.class.getName(), nameMethod, MessageFormat.format("The credit card was successfully removed: {0}", id));
            } else {
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                logBack.addLog(ERRORTYPEMESSAGE, PersonService.class.getName(), nameMethod, MessageFormat.format("Attention unregistered credit card, Data: {0}", id));
            }
        } catch (Exception ex) {
            httpStatus = HttpStatus.CONFLICT;
            logBack.addLog(ERRORTYPEMESSAGE, CreditCardService.class.getName(), nameMethod, MessageFormat.format("Attention an error occurred when deleting the credit card, the cause is: {0} Cause: {1} StackTrace: {2} Data: {3}", ex.getMessage(), ex.getCause(), ex.getStackTrace(), id));
        }
        return new ResponseEntity<>(id, httpStatus);
    }
}
