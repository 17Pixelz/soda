package com.ebanking.exception;

import com.ebanking.models.Client;
import com.ebanking.models.Wallet;

public class WalletDoesNotBelongToCustomer extends Exception {
    public WalletDoesNotBelongToCustomer(Client customer, Wallet wallet) {
        super("Customer with id"+customer.getIduser()+" does not have associated walletId : "+wallet.getWalletId());
    }
}
