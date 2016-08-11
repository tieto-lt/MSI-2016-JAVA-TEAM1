package lt.tieto.msi2016.order.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.order.repository.model.OrdersDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;

@Repository
public class OrdersRepository extends BaseRepository<OrdersDb> {

    private static final RowMapper<OrdersDb> ROW_MAPPER = (rs, rowNum) -> {
        OrdersDb order = new OrdersDb();
        order.setId(rs.getLong("id"));
        order.setCreatedBy(rs.getLong("created_by"));
        order.setMissionName(rs.getString("mission_name"));
        order.setFullName(rs.getString("full_name"));
        order.setPhone(rs.getString("phone"));
        order.setEmail(rs.getString("email"));
        order.setSubmissionDate(new DateTime(rs.getTimestamp("submission_date")));
        order.setDetails(rs.getString("details"));
        order.setOrderState(OrdersDb.OrderState.valueOf(rs.getString("order_state")));

        return order;
    };

    private static final RowUnmapper<OrdersDb> ROW_UNMAPPER = ordersDb -> mapOf(
            "id", ordersDb.getId(),
            "created_by", ordersDb.getCreatedBy(),
            "mission_name", ordersDb.getMissionName(),
            "full_name", ordersDb.getFullName(),
            "phone", ordersDb.getPhone(),
            "email", ordersDb.getEmail(),
            "submission_date", new Timestamp(ordersDb.getSubmissionDate().getMillis()),
            "details", ordersDb.getDetails(),
            "order_state", ordersDb.getOrderState().toString()

    );

    public OrdersRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "orders", "id");
    }
}