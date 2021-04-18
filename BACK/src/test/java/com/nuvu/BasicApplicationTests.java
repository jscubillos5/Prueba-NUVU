package com.nuvu;

import com.nuvu.controllers.CreditCardController;
import com.nuvu.controllers.PersonController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
class BasicApplicationTests {

    @Test
    void testPersonControllerHome() {
        PersonController personController = new PersonController();
        String result = personController.home();
        assertFalse(result.isEmpty());
    }

    @Test
    void testCreditCardControllerHome() {
        CreditCardController creditCardController = new CreditCardController();
        String result = creditCardController.home();
        assertFalse(result.isEmpty());
    }
}
