package com.ebanking.exception;

import com.ebanking.constant.Constants;

public class OutOfLimitAmountTransfert extends Exception{
    public OutOfLimitAmountTransfert(){
        super("Max amount by Transfert is :"+ Constants.PLAFONT_TRANSFERT_WALLET_CL);
    }
}
