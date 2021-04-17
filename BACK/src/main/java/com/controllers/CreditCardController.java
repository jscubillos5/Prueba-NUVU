package com.controllers;

import com.dto.CreditCardDto;
import com.model.CreditCard;
import com.service.CreditCardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

@RestController
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @ApiOperation(value = "Permite identificar si el controlador de tarjetas de credito esta arriba")
    @RequestMapping("CreditCard/home")
    public String home() {
        return "Home Credit Card";
    }

    @ApiOperation(value = "Retorna la lista de tarjetas de credito asociadas a una persona")
    @GetMapping(
            path = "CreditCard/get-credit-cards",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<List<CreditCard>> getCreditCardByPerson(@Valid @RequestParam("idPerson") Long idPerson) {
        return creditCardService.getCreditCardByPerson(idPerson);
    }

    @ApiOperation(value = "Permite registrar una tarjeta de credito en el sistema")
    @PostMapping(
            path = "CreditCard/add-credit-card",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<CreditCard> addCreditCard(@RequestBody CreditCardDto creditCardDto) {
        return creditCardService.addCreditCard(creditCardDto);
    }

    @ApiOperation(value = "Permite eliminar una tarjeta de credito en el sistema")
    @PostMapping(
            path = "CreditCard/delete-credit-card",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<Long> deleteCreditCard(@Valid @RequestParam("id") Long id) {
        return creditCardService.deleteCreditCard(id);
    }
}
