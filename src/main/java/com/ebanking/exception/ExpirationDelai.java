package com.ebanking.exception;

import com.ebanking.models.Client;
import com.ebanking.models.WaletTransaction;

public class ExpirationDelai extends Exception {
	public ExpirationDelai(int trcode) {
        super("le délai de transfert  "+trcode+"est expiré");
    }
}
