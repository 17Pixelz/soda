package com.ebanking.exception;

import com.ebanking.constant.Constants;
import com.ebanking.models.CompteCl;

public class OutOfLimitAmountTrnsfertAnnuel extends Exception{
    public OutOfLimitAmountTrnsfertAnnuel(CompteCl compteCl){
        super("U have already reached the Limit of Tranactions per year :"+ Constants.PLAFONT_TRANSFERT_WALLET_CL_ANNUEL+
                " Sorry u have to wait until : " + compteCl.getDate_premierTransfert());
    }
}
