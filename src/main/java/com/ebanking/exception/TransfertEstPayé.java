package com.ebanking.exception;

import com.ebanking.models.Client;
import com.ebanking.models.WaletTransaction;

public class TransfertEstPayé extends Exception {
    public TransfertEstPayé(int transfertcode) {
        super("le transfert  "+transfertcode+"est payée");
    }
}
