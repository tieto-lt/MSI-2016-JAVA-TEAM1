package lt.tieto.msi2016.mission.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.mission.repository.model.MissionResultDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by it11 on 16.8.9.
 */


@Repository
public class MissionResult extends BaseRepository<MissionResultDb> {

    private static final RowMapper<MissionResultDb> ROW_MAPPER = (rs, rowNum) -> {
        MissionResultDb item = new MissionResultDb();
        item.setId(rs.getLong("mission_id"));
        item.setOrderId(rs.getLong("order_id"));
        item.setExecutedBy(rs.getLong("executed_by"));
        item.setExecutionDate(rs.getString("execution_date"));
        item.setBatteryStatus(rs.getLong("battery_status"));
        item.setVideoBase64(rs.getString("video_base_64"));
        item.setImages(rs.getString("images"));
        item.setNavigationData(rs.getString("navigation_data"));
        item.setMissionState(MissionResultDb.MissionState.valueOf(rs.getString("mission_state")));
        return item;
    };

    private static final RowUnmapper<MissionResultDb> ROW_UNMAPPER = MissionResultDb -> mapOf(
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

    public MissionResult() {
        super(ROW_MAPPER, ROW_UNMAPPER, "mission_results", "mission_id");
    }
}