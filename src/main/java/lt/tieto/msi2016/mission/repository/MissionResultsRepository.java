package lt.tieto.msi2016.mission.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.mission.repository.model.MissionResultsDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MissionResultsRepository extends BaseRepository<MissionResultsDb> {

    private static final RowMapper<MissionResultsDb> ROW_MAPPER = (rs, rowNum) -> {
        MissionResultsDb item = new MissionResultsDb();
        item.setId(rs.getLong("id"));
        item.setMissionId(rs.getLong("mission_id"));
        item.setOrderId(rs.getLong("order_id"));
        item.setExecutedBy(rs.getLong("executed_by"));
        item.setExecutionDate(new DateTime(rs.getTimestamp("execution_date")));
        item.setBatteryStatus(rs.getBigDecimal("battery_status"));
        item.setVideoBase64(rs.getString("video_base64"));
        item.setImages(rs.getString("images"));
        item.setNavigationData(rs.getString("navigation_data"));
        item.setMissionState(MissionResultsDb.MissionState.valueOf(rs.getString("mission_state")));
        return item;
    };

    private static final RowUnmapper<MissionResultsDb> ROW_UNMAPPER = MissionResultDb -> mapOf(
            "id", MissionResultDb.getId(),
            "mission_id", MissionResultDb.getMissionId(),
            "order_id", MissionResultDb.getOrderId(),
            "executed_by", MissionResultDb.getExecutedBy(),
            "execution_date", MissionResultDb.getExecutionDate(),
            "battery_status", MissionResultDb.getBatteryStatus(),
            "video_base64", MissionResultDb.getVideoBase64(),
            "images", MissionResultDb.getImages(),
            "navigation_data", MissionResultDb.getNavigationData(),
            "mission_state", MissionResultDb.getMissionState().toString()
    );

    public MissionResultsRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "mission_results", "id");
    }
}