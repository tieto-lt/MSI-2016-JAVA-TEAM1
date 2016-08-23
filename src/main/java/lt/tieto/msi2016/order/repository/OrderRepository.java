package lt.tieto.msi2016.order.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.mission.model.MissionCommand;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.user.repository.model.UserDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderRepository extends BaseRepository<OrderDb> {


    @Autowired
    private JdbcTemplate template;
    public static final String SELECT_ORDER_USER_ID = "SELECT * FROM orders where submitted_by = ?";
    public static final String SELECT_BY_STATUS = "SELECT * FROM orders where status = ?";
    public static final String SELECT_SORTED_ORDERS = "SELECT * FROM orders";


    private static final RowMapper<OrderDb> ROW_MAPPER = (rs, rowNum) -> {
        OrderDb order = new OrderDb();
        order.setId(rs.getLong("id"));
        order.setSubmittedBy(rs.getLong("submitted_by"));
        order.setMissionId(rs.getString("mission_id"));
        order.setSubmissionDate(new DateTime(rs.getTimestamp("submission_date")));
        order.setDetails(rs.getString("details"));
        order.setStatus(OrderDb.Status.valueOf(rs.getString("status")));
        order.setCommands(rs.getString("commands"));
        order.setFullName(rs.getString("full_name"));
        order.setEmail(rs.getString("email"));
        order.setPhone(rs.getString("phone"));
        return order;
    };

    private static final RowUnmapper<OrderDb> ROW_UNMAPPER = orderDb -> mapOf(
            "id", orderDb.getId(),
            "submitted_by", orderDb.getSubmittedBy(),
            "mission_id", orderDb.getMissionId(),
            "submission_date", new Timestamp(orderDb.getSubmissionDate().getMillis()),
            "details", orderDb.getDetails(),
            "status", orderDb.getStatus().toString(),
            "commands", orderDb.getCommands(),
            "full_name", orderDb.getFullName(),
            "email", orderDb.getEmail(),
            "phone", orderDb.getPhone()
    );

    public OrderRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "orders", "id");
    }

    public List <OrderDb> getOrdersByUserId (Long user_id){
        List <OrderDb>  ordersOfCustomer =template.query(SELECT_ORDER_USER_ID, new Object[] {user_id}, ROW_MAPPER);
        return ordersOfCustomer;
    }

    public List<OrderDb> getOrdersByStatus(OrderDb.Status status) {
        return template.query(SELECT_BY_STATUS, new Object[]{status.toString()}, ROW_MAPPER);
    }

    public List<OrderDb> getOrdersSortedByStatus() {
        return template.query(SELECT_SORTED_ORDERS, new Object[]{}, ROW_MAPPER);
    }
}