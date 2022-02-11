package com.ebanking.comparator;

import java.util.Comparator;
import com.ebanking.models.*;
public class walletTransactionSortingComparator implements Comparator<WaletTransaction> {

    public int compare(WaletTransaction t1, WaletTransaction t2) {
        return t2.getDateT().compareTo(t1.getDateT());
    }
}
