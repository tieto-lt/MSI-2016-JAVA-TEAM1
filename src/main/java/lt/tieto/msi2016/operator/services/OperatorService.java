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

    public Boolean isOperatorValidByToken(String token){
        return repository.operatorByToken(token) != null;
    }

    private OperatorModel updateOperatorStatus(String token ,OperatorStatus.Status status) {
        OperatorDb model  = repository.operatorByToken(token);
        if (model != null) {
            model.setStatus(status);

            OperatorDb updated = repository.update(model);
            return mapToOperator(updated);
        }
        return null;
    }

    private boolean isMissionSuccessful(MissionResult missionResult) {
        List<MissionImage> images=missionResult.getImages();
        return !images.isEmpty();
    }




    public String generateToken(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        Long id = securityService.getCurrentUser().getId();
        OperatorModel operator = new OperatorModel();
        operator.setUserId(id);
        operator.setToken(token);
        operator.setStatus(OperatorStatus.Status.NOTVERIFIED);

       OperatorDb operatorDb = repository.operatorByUserID(id);
        if (operatorDb == null){
            repository.create(mapToOperatorDb(operator));
        } else if (operatorDb.getStatus()== OperatorStatus.Status.NOTVERIFIED){
            operator.setId(operatorDb.getId());
            repository.update(mapToOperatorDb(operator));
        } else if (operatorDb.getStatus()== OperatorStatus.Status.VERIFIED){
            operator.setId(operatorDb.getId());
            operator.setStatus(OperatorStatus.Status.VERIFIED);
            repository.update(mapToOperatorDb(operator));
        }

        return token;
    }

    public OperatorStatus.Status getOperatorStatus(){
        Long id = securityService.getCurrentUser().getId();
        OperatorDb operatorDb = repository.operatorByUserID(id);
        return operatorDb.getStatus();
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
