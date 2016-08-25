package lt.tieto.msi2016.transaction.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.transaction.repository.model.PaymentsDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PaymentsRepository  extends BaseRepository<PaymentsDb> {

    @Autowired
    private JdbcTemplate template;


    private static final RowMapper<PaymentsDb> ROW_MAPPER = (rs, rowNum) -> {
        PaymentsDb payment = new PaymentsDb();
        payment.setId(rs.getLong("id"));
        payment.setPayseraOrderId(rs.getString("paysera_order_id"));
        payment.setUserId(rs.getLong("user_id"));
        payment.setAmount(rs.getBigDecimal("amount"));
        payment.setStatus(PaymentsDb.Status.valueOf(rs.getString("status")));
        return payment;
    };

    private static final RowUnmapper<PaymentsDb> ROW_UNMAPPER = paymentsDb -> mapOf(
            "id", paymentsDb.getId(),
            "paysera_order_id", paymentsDb.getPayseraOrderId(),
            "user_id", paymentsDb.getUserId(),
            "amount", paymentsDb.getAmount(),
            "status", paymentsDb.getStatus().toString()
    );

    public PaymentsRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "payments", "id");
    }
}
