package com.pddstudio.pocketlibrary;
/*
 * This Class was created by Patrick J
 * on 09.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import com.pddstudio.pocketlibrary.models.Transaction;

import java.util.LinkedList;
import java.util.List;

import io.paperdb.Paper;

public class TransactionManager {

    private static final String TRANSACTION_ITEMS = "mTransactionItems";

    protected TransactionManager() {}

    private List<Transaction> getCurrentTransactionList() {
        return Paper.book().read(TRANSACTION_ITEMS, new LinkedList<Transaction>());
    }

    public List<Transaction> getFilteredTransactionList(int forMonth, int forYear) {
        List<Transaction> transactions = new LinkedList<>();
        for(Transaction transaction : getCurrentTransactionList()) {
            if(transaction.getTransactionDate().getMonth() == forMonth && transaction.getTransactionDate().getYear() == forYear) transactions.add(transaction);
        }
        return transactions;
    }

}
