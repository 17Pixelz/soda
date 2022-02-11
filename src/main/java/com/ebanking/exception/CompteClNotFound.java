package com.ebanking.exception;

public class CompteClNotFound extends RuntimeException{
    public CompteClNotFound(){
        super("Account Client not found");
    }
}
