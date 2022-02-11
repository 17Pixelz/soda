package com.ebanking.exception;

import com.ebanking.constant.Constants;

public class OutOfLimitAmountTrnsfert extends Exception {

    public OutOfLimitAmountTrnsfert(int walletId) {
        super("Wallet with walletId : "+walletId+" is out of amount  transfert limit "+Constants.PLAFONT_TRANSFERT_WALLET );
    }
}
