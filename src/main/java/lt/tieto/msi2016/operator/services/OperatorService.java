package lt.tieto.msi2016.operator.services;

import lt.tieto.msi2016.mission.model.operator.MissionImage;
import lt.tieto.msi2016.mission.model.operator.MissionResult;
import lt.tieto.msi2016.operator.OperatorStatus;
import lt.tieto.msi2016.operator.model.OperatorModel;
import lt.tieto.msi2016.operator.repository.OperatorVerification;
import lt.tieto.msi2016.operator.repository.model.OperatorDb;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OperatorService {


    @Autowired
    private SecurityService securityService;

    @Autowired
    private OperatorVerification repository;

    public OperatorModel verifyAndUpdateStatus(MissionResult missionResult, String token) {
        if (isMissionSuccessful( missionResult)){
            updateOperatorStatus(token , OperatorStatus.Status.VERIFIED);
        }

        return null;
    }


    private OperatorModel updateOperatorStatus(String token ,OperatorStatus.Status status) {
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
        operator.setUserId(id);
        operator.setToken(token);
        operator.setStatus(OperatorStatus.Status.TOKENISSUE);
        // Saves generated token to database
    /*    OperatorDb operatorDb=repository.operatorByUserID(id);

        if(operatorDb.getStatus() == null){
            operatorDb.setStatus(OperatorDb.OperatorStatus.NONVERIFIED);
        }

        OperatorModel operator=mapToOperator(operatorDb);*/

        OperatorDb operatorDb=repository.operatorByUserID(id);

        repository.create(mapToOperatorDb(operator));


        return token;
    }

    public OperatorModel getOperatorStatus(){
        Long id = securityService.getCurrentUser().getId();
        OperatorModel operator = new OperatorModel();
        operator.setUserId(id);
        operator.setToken("test-token");
        operator.setStatus(OperatorStatus.Status.TOKENISSUE);
        return operator;
    }

    private static OperatorModel mapToOperator(OperatorDb db) {
        OperatorModel api = new OperatorModel();
        api.setId(db.getId());
        api.setUserId(db.getUserId());
        api.setToken(db.getToken());
        api.setStatus(OperatorStatus.Status.valueOf(db.getStatus().toString()));



        return api;
    }

    private static OperatorDb mapToOperatorDb(Long id, OperatorModel api) {
        OperatorDb db = new OperatorDb();
        db.setId(id);
        db.setToken(api.getToken());
        db.setUserId(api.getUserId());
        db.setStatus(OperatorStatus.Status.valueOf(api.getStatus().toString()));
        return db;
    }



    private static OperatorDb mapToOperatorDb(OperatorModel api) {
        return mapToOperatorDb(api.getId(), api);
    }




}
