package com.ebanking.exception;

public class TransactionClNotFound extends Exception{
    public TransactionClNotFound(){
        super("Transaction by client not found");
    }
}
