package lt.tieto.msi2016.mission.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.mission.repository.model.MissionResultsDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MissionResults extends BaseRepository<MissionResultsDb> {

    private static final RowMapper<MissionResultsDb> ROW_MAPPER = (rs, rowNum) -> {
        MissionResultsDb item = new MissionResultsDb();
        item.setId(rs.getLong("mission_id"));
        item.setOrderId(rs.getLong("order_id"));
        item.setExecutedBy(rs.getLong("executed_by"));
        item.setExecutionDate(rs.getString("execution_date"));
        item.setBatteryStatus(rs.getLong("battery_status"));
        item.setVideoBase64(rs.getString("video_base_64"));
        item.setImages(rs.getString("images"));
        item.setNavigationData(rs.getString("navigation_data"));
        item.setMissionState(MissionResultsDb.MissionState.valueOf(rs.getString("mission_state")));
        return item;
    };

    private static final RowUnmapper<MissionResultsDb> ROW_UNMAPPER = MissionResultDb -> mapOf(
            "mission_id", MissionResultDb.getId(),
            "order_id", MissionResultDb.getOrderId(),
            "executed_by", MissionResultDb.getExecutedBy(),
            "execution_date", MissionResultDb.getExecutionDate(),
            "battery_status", MissionResultDb.getBatteryStatus(),
            "video_base_64", MissionResultDb.getVideoBase64(),
            "images", MissionResultDb.getImages(),
            "navigation_data", MissionResultDb.getNavigationData(),
            "mission_state", MissionResultDb.getMissionState()



    );

    public MissionResults() {
        super(ROW_MAPPER, ROW_UNMAPPER, "mission_results", "mission_id");
    }
}