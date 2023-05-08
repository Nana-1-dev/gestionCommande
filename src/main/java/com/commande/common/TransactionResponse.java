package com.commande.common;


import com.commande.model.Commande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Commande commande;
    private double montant;
    private String transactionId;
    private String message;
}