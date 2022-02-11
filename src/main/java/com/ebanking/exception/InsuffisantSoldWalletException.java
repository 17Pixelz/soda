package com.ebanking.exception;
 

public class InsuffisantSoldWalletException extends Exception {

    public InsuffisantSoldWalletException(int walletId) {
        super("Wallet with walletId : "+walletId+" does not have sufficient balance");
    }
}
