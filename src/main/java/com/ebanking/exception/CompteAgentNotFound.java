package com.ebanking.exception;

public class CompteAgentNotFound extends RuntimeException{
    public CompteAgentNotFound(){
        super("Ageent Account not found");
    }
}
