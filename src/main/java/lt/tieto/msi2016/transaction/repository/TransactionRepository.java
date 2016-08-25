package lt.tieto.msi2016.transaction.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.transaction.repository.model.TransactionDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by it11 on 16.8.24.
 */
@Repository
public class TransactionRepository extends BaseRepository<TransactionDb>{


    @Autowired
    private JdbcTemplate template;

    public static final String SELECT_BALANCE_BY_USER_ID = "SELECT * FROM transactions where user_id = ?";


    private static final RowMapper<TransactionDb> ROW_MAPPER=(rs,rowNum)->{
      TransactionDb transactionDb = new TransactionDb();
        transactionDb.setId(rs.getLong("id"));
        transactionDb.setUserId(rs.getLong("user_id"));
        transactionDb.setTransaction(rs.getBigDecimal("transaction"));
        return  transactionDb;
    };

    private static final RowUnmapper <TransactionDb> DB_ROW_UNMAPPER =transactionDb -> mapOf(
            "id", transactionDb.getId(),
            "user_id", transactionDb.getUserId(),
            "transaction", transactionDb.getTransaction()
    );



    public TransactionRepository () {super (ROW_MAPPER, DB_ROW_UNMAPPER,"transactions","id");}

    public List<TransactionDb> getUserTransactions (Long userId){
        List<TransactionDb> transactions =template.query(SELECT_BALANCE_BY_USER_ID, new Object[]{userId}, ROW_MAPPER);
        return transactions;
    }





}
