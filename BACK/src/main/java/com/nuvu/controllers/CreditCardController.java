package com.nuvu.controllers;

import com.nuvu.dto.CreditCardDto;
import com.nuvu.model.CreditCard;
import com.nuvu.service.CreditCardService;
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
@RequestMapping(path = "/CreditCard/")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @ApiOperation(value = "Permite identificar si el controlador de tarjetas de credito esta arriba")
    @RequestMapping("home")
    public String home() {
        return "Home Credit Card";
    }

    @ApiOperation(value = "Retorna la lista de tarjetas de credito asociadas a una persona")
    @GetMapping(
            path = "get-credit-cards",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<List<CreditCard>> getCreditCardByPerson(@Valid @RequestParam("idPerson") Long idPerson) {
        return creditCardService.getCreditCardByPerson(idPerson);
    }

    @ApiOperation(value = "Permite registrar una tarjeta de credito en el sistema")
    @PostMapping(
            path = "add-credit-card",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<CreditCard> addCreditCard(@RequestBody CreditCardDto creditCardDto) {
        return creditCardService.addCreditCard(creditCardDto);
    }

    @ApiOperation(value = "Permite eliminar una tarjeta de credito en el sistema")
    @PostMapping(
            path = "delete-credit-card",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    HttpEntity<Long> deleteCreditCard(@Valid @RequestParam("id") Long id) {
        return creditCardService.deleteCreditCard(id);
    }
}
