package lt.tieto.msi2016.transaction.service;

import lt.tieto.msi2016.transaction.model.Transaction;
import lt.tieto.msi2016.transaction.repository.model.TransactionDb;

import java.io.IOException;

/**
 * Created by it11 on 16.8.24.
 */
public class TransactionService {














    private Transaction mapToTransaction (TransactionDb db) throws IOException{
        Transaction api= new Transaction();
        api.setId(db.getId());
        api.setUserId(db.getUserId());
        api.setTransaction(db.getTransaction());
        api.setBalance(db.getBalance());
        return api;
    }


    private TransactionDb mapToTrancactionDB ()


}

