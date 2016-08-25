package lt.tieto.msi2016.transaction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lt.tieto.msi2016.transaction.model.Transaction;
import lt.tieto.msi2016.transaction.repository.TransactionRepository;
import lt.tieto.msi2016.transaction.repository.model.TransactionDb;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@Service
public class TransactionService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TransactionRepository repository;




    public BigDecimal getBalance() {
        Long userId = securityService.getCurrentUser().getId();

        BigDecimal balance= BigDecimal.ZERO;
        List<TransactionDb> userTransactions = repository.getUserTransactions(userId);
        for (TransactionDb transaction: userTransactions) {
            balance = balance.add(transaction.getTransaction());
        }

        return balance;

    }

    private Transaction mapToTransaction (TransactionDb db) throws IOException{
        Transaction api= new Transaction();
        api.setId(db.getId());
        api.setUserId(db.getUserId());
        api.setTransaction(db.getTransaction());
        return api;
    }


    private TransactionDb mapToTrancactionDB (Long id, Transaction api, Long userId)throws JsonProcessingException{
        TransactionDb db=new TransactionDb();
        db.setId(id);
        db.setUserId(userId);
        db.setTransaction(api.getTransaction());

        return db;
    }

    private TransactionDb mapToTrancactionDB(Transaction api, Long userId) throws  JsonProcessingException{
        return mapToTrancactionDB(null,api,userId);
    }

}

