package lt.tieto.msi2016.transaction.repository;

import lt.tieto.msi2016.utils.repository.BaseRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.transaction.repository.model.TransactionDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static lt.tieto.msi2016.utils.repository.BaseRepository.mapOf;


/**
 * Created by it11 on 16.8.24.
 */
public class TransactionRepository extends BaseRepository<TransactionDb>{


    @Autowired
    private JdbcTemplate template;

    public static final String SELECT_BALANCE_BY_USER_ID = "SELECT transaction FROM transactions where user_id = ?";


    private static final RowMapper<TransactionDb> ROW_MAPPER=(rs,rowNum)->{
      TransactionDb transactionDb = new TransactionDb();
        transactionDb.setId(rs.getLong("id"));
        transactionDb.setUserId(rs.getLong("user_id"));
        transactionDb.setTransaction(rs.getFloat("transaction"));
        return  transactionDb;
    };

    private static final RowUnmapper <TransactionDb> DB_ROW_UNMAPPER =transactionDb -> mapOf(
            "id", transactionDb.getId(),
            "user_id", transactionDb.getUserId(),
            "transaction", transactionDb.getTransaction()


    );



    public TransactionRepository () {super (ROW_MAPPER, DB_ROW_UNMAPPER,"transactions","id");}

    public float getUserBalance (Long userId){
        float balance=0;
        List<TransactionDb> transactions =template.query(SELECT_BALANCE_BY_USER_ID, new Object[]{userId}, ROW_MAPPER);

        for (TransactionDb transaction: transactions) balance += transaction.getTransaction();

        return balance;
    }





}
