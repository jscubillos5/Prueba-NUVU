package com.nuvu.repositories;

import com.nuvu.model.CreditCard;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {

    CreditCard findByNumber(int number);
}
