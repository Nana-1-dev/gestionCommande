package com.commande.rest;

import com.commande.common.Payment;
import com.commande.common.TransactionRequest;
import com.commande.common.TransactionResponse;
import com.commande.model.Commande;
import com.commande.service.CommandeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commande")
public class CommandeControler {
    @Autowired
    private CommandeService commandeService;

@PostMapping("/commande")
//faites un appel au paiement et passez l'identifiant de la commande

    public TransactionResponse enregistreCommande(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return commandeService.enregistreCommande(request );
    }

}
