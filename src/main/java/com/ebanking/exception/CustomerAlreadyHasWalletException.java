package com.ebanking.exception;

import com.ebanking.models.Client;

public class CustomerAlreadyHasWalletException extends Exception {
    public CustomerAlreadyHasWalletException(Client customer) {
        super("Customer "+customer.getPrenomClient()+" "+customer.getNomclient()+" already owns a wallet : "+customer.getCompte().getWalletPorteur().getWalletId());
    }
}
