package com.ebanking.exception;

import com.ebanking.models.Client;
import com.ebanking.models.WaletTransaction;

public class TransfertDoesNotExistException extends Exception {
    public TransfertDoesNotExistException(int transfertcode) {
        super("Le transfert  "+transfertcode+"  n'existe pas : ");
    }
}
