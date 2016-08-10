package lt.tieto.msi2016.operator.services;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.mission.model.MissionImage;
import lt.tieto.msi2016.mission.model.MissionResult;
import lt.tieto.msi2016.operator.model.OperatorModel;
import lt.tieto.msi2016.operator.repository.OperatorVerification;
import lt.tieto.msi2016.operator.repository.model.OperatorDb;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.util.UUID;

@Service
public class OperatorService {


    @Autowired
    private SecurityService securityService;

    private OperatorVerification repository;

    public OperatorModel verifyAndUpdateStatus(MissionResult missionResult, String token) {
        if (isMissionSuccessful( missionResult)){
            updateOperatorStatus(token , OperatorDb.OperatorStatus.VERIFIED );
        }

        return null;
    }


    private OperatorModel updateOperatorStatus(String token ,OperatorDb.OperatorStatus status) {
        OperatorDb model  = repository.operatorByToken(token);
        model.setStatus(status);

        OperatorDb updated = repository.update(model);
        return mapToOperator(updated);
    }

    private boolean isMissionSuccessful(MissionResult missionResult/*, String token*/) {
        List<MissionImage> images=missionResult.getImages();
        return !images.isEmpty();
    }




    public String generateToken(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        Long id = securityService.getCurrentUser().getId();
        OperatorModel operator = new OperatorModel();
        operator.setOperatorID(id);
        operator.setToken(token);
        // Please save me to DB, thanks.

        return token;
    }

    public OperatorModel getOperatorStatus(){
        Long id = securityService.getCurrentUser().getId();
        OperatorModel operator = new OperatorModel();
        operator.setOperatorID(id);
        operator.setToken("test-token");
        operator.setStatus(OperatorModel.Status.VERIFIED);
        return operator;
    }

    private static OperatorModel mapToOperator(OperatorDb db) {
        OperatorModel api = new OperatorModel();
        api.setOperatorID(db.getId());
        api.setToken(db.getToken());
        api.setStatus(OperatorDb.OperatorStatus.valueOf(db.getStatus())  );

        return api;
    }

    private static OperatorDb mapToOperatorDb(Long id, OperatorModel api) {
        OperatorDb db = new OperatorDb();
        db.setId(id);
        db.setToken(api.getToken());
        db.setStatus(api.getOperatorStatus();
        return db;
    }



    private static OperatorDb mapToOperatorDb(OperatorModel api) {
        return mapToOperatorDb(api.getOperatorID(), api);
    }




}
