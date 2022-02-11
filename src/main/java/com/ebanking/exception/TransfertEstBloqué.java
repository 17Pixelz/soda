package com.ebanking.exception;

import com.ebanking.models.Client;
import com.ebanking.models.WaletTransaction;

public class TransfertEstBloqué extends Exception {
    public TransfertEstBloqué(int transfertcode) {
        super("le transfert  "+transfertcode+"est Bloqué");
    }
}
