package com.ebanking.exception;

public class TransactionAgentNotFound extends Exception {
    public TransactionAgentNotFound(){
        super("Transaction by agent not found");
    }
}
