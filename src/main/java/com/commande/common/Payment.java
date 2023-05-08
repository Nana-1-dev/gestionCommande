package com.commande.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment  {

    private Long paymentId;
    private  String paymentStatus;
    private String transactionId;
    private Long commandeId;
    private double montant;


}
