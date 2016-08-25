package lt.tieto.msi2016.transaction.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.transaction.repository.model.PaymentsDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentsRepository  extends BaseRepository<PaymentsDb> {

    public static final String SELECT_PAYMENTS_BY_ORDER_ID = "SELECT * FROM payments where paysera_order_id = ?";

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

    public PaymentsDb getPaymentByOrderId (String orderId){
        List<PaymentsDb> payments =template.query(SELECT_PAYMENTS_BY_ORDER_ID, new Object[]{orderId}, ROW_MAPPER);
        if (payments.isEmpty()) {
            return null;
        } else {
            return payments.get(0);
        }
    }
}
