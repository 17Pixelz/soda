package com.ebanking.exception;
 
public class CustomerDoesNotExistException extends Exception {

    public CustomerDoesNotExistException(int customerId) {
        super("Customer with customer ID:" + customerId + " does not exist");
    }
}
