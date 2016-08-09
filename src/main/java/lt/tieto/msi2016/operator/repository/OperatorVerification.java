package lt.tieto.msi2016.operator.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.operator.repository.model.OperatorDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by it11 on 16.8.8.
 */



@Repository
public class OperatorVerification extends BaseRepository <OperatorDb > {

    private static final RowMapper<OperatorDb > ROW_MAPPER = (rs, rowNum) -> {
        OperatorDb  item = new OperatorDb ();
        item.setId(rs.getLong("id"));
        item.setStatus(OperatorDb.OperatorStatus.valueOf(rs.getString("status")));
        item.setToken(rs.getString("token"));
        item.setUserId(rs.getLong("user_id"));
        return item;
    };

    private static final RowUnmapper<OperatorDb > ROW_UNMAPPER = operatorDb  -> mapOf(
            "id", operatorDb.getId(),
            "status", operatorDb.getStatus(),
            "token", operatorDb.getToken(),
            "user_id", operatorDb.getUserId()


    );

    public OperatorVerification() {
        super(ROW_MAPPER, ROW_UNMAPPER, "operator_verification", "id");
    }
}