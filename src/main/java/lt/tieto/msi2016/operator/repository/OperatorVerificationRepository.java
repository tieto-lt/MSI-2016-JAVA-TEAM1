package lt.tieto.msi2016.operator.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.operator.OperatorVerificationStatus;
import lt.tieto.msi2016.operator.repository.model.OperatorVerificationDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by it11 on 16.8.8.
 */



@Repository
public class OperatorVerificationRepository extends BaseRepository <OperatorVerificationDb> {


    public static final String SELECT_OPERATOR_TOKEN = "SELECT * FROM operator_verification where token = ?";
    public static final String SELECT_OPERATOR_USER_ID = "SELECT * FROM operator_verification where user_id = ?";
    @Autowired
    private JdbcTemplate template;



    private static final RowMapper<OperatorVerificationDb> ROW_MAPPER = (rs, rowNum) -> {
        OperatorVerificationDb item = new OperatorVerificationDb();
        item.setId(rs.getLong("id"));
        item.setToken(rs.getString("token"));
        item.setUserId(rs.getLong("user_id"));
        item.setStatus(OperatorVerificationStatus.Status.valueOf(rs.getString("status")));
        return item;
    };

    private static final RowUnmapper<OperatorVerificationDb> ROW_UNMAPPER = operatorVerificationDb -> mapOf(
            "id", operatorVerificationDb.getId(),
            "status", operatorVerificationDb.getStatus().toString(),
            "token", operatorVerificationDb.getToken(),
            "user_id", operatorVerificationDb.getUserId()
    );

    public OperatorVerificationRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "operator_verification", "id");
    }

    public OperatorVerificationDb operatorByToken(String token) {
        List<OperatorVerificationDb> list = template.query(SELECT_OPERATOR_TOKEN, new Object[]{token}, ROW_MAPPER);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public OperatorVerificationDb operatorByUserID(Long user_id) {
        List <OperatorVerificationDb> selectedOperators = template.query(SELECT_OPERATOR_USER_ID, new Object[] {user_id} , ROW_MAPPER);
        if(!selectedOperators.isEmpty()){
            return selectedOperators.get(0);
        }
        return null;
    }


}