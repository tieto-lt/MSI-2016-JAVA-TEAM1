package lt.tieto.msi2016.mission.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.mission.repository.model.MissionDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by it11 on 16.8.9.
 */


@Repository
public class Mission extends BaseRepository<MissionDb > {

    private static final RowMapper<MissionDb > ROW_MAPPER = (rs, rowNum) -> {
        MissionDb  item = new MissionDb ();
        item.setId(rs.getLong("mission_id"));
        item.setSubmitedBy(rs.getLong("submited_by"));
        item.setExecutedBy(rs.getLong("executed_by"));
        item.setSubmmissionDate(rs.getString("submmission_date"));
        item.setExecutionDate(rs.getString("execution_date"));
        item.setVideoBase64(rs.getString("video_base_64"));
        item.setImage(rs.getString("image"));
        item.setNavigationData(rs.getString("navigation_data"));
        item.setMissionState(MissionDb.MissionState.valueOf(rs.getString("mission_state")));
        return item;
    };

    private static final RowUnmapper<MissionDb > ROW_UNMAPPER = MissionDb  -> mapOf(
            "mission_id", MissionDb.getId(),
            "submited_by", MissionDb.getSubmitedBy(),
            "executed_by", MissionDb.getExecutedBy(),
            "submmission_date", MissionDb.getSubmmissionDate(),
            "execution_date", MissionDb.getExecutionDate(),
            "video_base_64", MissionDb.getVideoBase64(),
            "image", MissionDb.getImage(),
            "navigation_data", MissionDb.getNavigationData(),
            "mission_state", MissionDb.getMissionState()



    );

    public Mission() {
        super(ROW_MAPPER, ROW_UNMAPPER, "mission", "id");
    }
}