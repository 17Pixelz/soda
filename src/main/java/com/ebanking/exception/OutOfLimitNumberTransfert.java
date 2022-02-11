package com.ebanking.exception;

import com.ebanking.constant.Constants;

public class OutOfLimitNumberTransfert extends Exception {

    public OutOfLimitNumberTransfert(int walletId) {
        super("Wallet with walletId : "+walletId+" is out of number  transfert limit  "+Constants.PLAFONT_TRANSFERT_WALLET_ANNUEL );
    }
}
