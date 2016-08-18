package lt.tieto.msi2016.order.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.order.repository.model.OrderResultsDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Repository
public class OrderResultsRepository extends BaseRepository<OrderResultsDb> {

    private static final RowMapper<OrderResultsDb> ROW_MAPPER = (rs, rowNum) -> {
        OrderResultsDb orderResultsDb = new OrderResultsDb();
        orderResultsDb.setId(rs.getLong("id"));
        orderResultsDb.setExecutedBy(rs.getLong("executed_by"));
        orderResultsDb.setOrderId(rs.getLong("order_id"));
        orderResultsDb.setMissionName(rs.getString("mission_name"));
        orderResultsDb.setExecutionDate(new DateTime(rs.getDate("execution_date").getTime()));
        orderResultsDb.setBatteryStatus(rs.getBigDecimal("battery_status"));
        orderResultsDb.setImages(rs.getString("images"));
        orderResultsDb.setVideoBase64(rs.getString("video_base64"));
        orderResultsDb.setNavigationData(rs.getString("navigation_data"));
        return orderResultsDb;
    };

    private static final RowUnmapper<OrderResultsDb> ROW_UNMAPPER = MissionResultDb -> mapOf(
            "id", MissionResultDb.getId(),
            "executed_by", MissionResultDb.getExecutedBy(),
            "order_id", MissionResultDb.getOrderId(),
            "mission_name", MissionResultDb.getMissionName(),
            "execution_date", new Timestamp(MissionResultDb.getExecutionDate().getMillis()),
            "battery_status", MissionResultDb.getBatteryStatus(),
            "images", MissionResultDb.getImages(),
            "video_base64", MissionResultDb.getVideoBase64(),
            "navigation_data", MissionResultDb.getNavigationData()
    );

    public OrderResultsRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "orders_results", "id");
    }
}