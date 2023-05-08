package com.commande.service;

import com.commande.common.Payment;
import com.commande.common.TransactionRequest;
import com.commande.common.TransactionResponse;
import com.commande.model.Commande;
import com.commande.repository.CommandeRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;




@Service
@RefreshScope
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    @Lazy
private RestTemplate restTemplate;


   @Value("${microservice.payment-service.endpoints.endpoint.uri}")
   private String ENDPOINT_URL;

private Logger log= LoggerFactory.getLogger(CommandeService.class);


public TransactionResponse enregistreCommande(TransactionRequest request) throws JsonProcessingException {
    String response="";
    Commande commande=request.getCommande();
    Payment payment=request.getPayment();
    payment.setCommandeId(commande.getId());
    payment.setMontant(commande.getPrix());

    log.info("CommandeService request:{}",new ObjectMapper().writeValueAsString(request));
   Payment paymentResponse=restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);//relies les services de commande au service de payment
    log.info("Payment-service response from CommandeService Rest call:{}",new ObjectMapper().writeValueAsString(paymentResponse));
    response=paymentResponse.getPaymentStatus().equals("success")?"traitement du paiement réussi et place du commandant":"il s'agit d'une défaillance de l'api de paiement, commande ajoutée au panier";
//APPEL DE REPOS

    commandeRepository.save(commande);

   return new TransactionResponse(commande,paymentResponse.getMontant(),paymentResponse.getTransactionId(),response);
}

}
