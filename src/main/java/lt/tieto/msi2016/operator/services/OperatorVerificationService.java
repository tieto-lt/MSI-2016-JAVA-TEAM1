package lt.tieto.msi2016.operator.services;

import lt.tieto.msi2016.mission.model.MissionImage;
import lt.tieto.msi2016.mission.model.MissionResult;
import lt.tieto.msi2016.operator.OperatorVerificationStatus;
import lt.tieto.msi2016.operator.model.OperatorVerification;
import lt.tieto.msi2016.operator.repository.OperatorVerificationRepository;
import lt.tieto.msi2016.operator.repository.model.OperatorVerificationDb;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OperatorVerificationService {


    @Autowired
    private SecurityService securityService;

    @Autowired
    private OperatorVerificationRepository repository;

    public OperatorVerification verifyAndUpdateStatus(MissionResult missionResult, String token) {
        if (isMissionSuccessful( missionResult)){
            updateOperatorStatus(token , OperatorVerificationStatus.Status.VERIFIED);
        }

        return null;
    }

    @Transactional
    public OperatorVerificationDb getOperatorByToken(String token) {
        return repository.getOperatorByToken(token);
    }

    public Boolean isOperatorValidByToken(String token){
        return repository.getOperatorByToken(token) != null;
    }

    private OperatorVerification updateOperatorStatus(String token , OperatorVerificationStatus.Status status) {
        OperatorVerificationDb model  = repository.getOperatorByToken(token);
        if (model != null) {
            model.setStatus(status);

            OperatorVerificationDb updated = repository.update(model);
            return mapToOperator(updated);
        }
        return null;
    }

    private boolean isMissionSuccessful(MissionResult missionResult) {
        List<MissionImage> images=missionResult.getImages();
        return !images.isEmpty();
    }

    public OperatorVerification createOperatorVerificationStatus(Long userId){
        OperatorVerification operator = new OperatorVerification();
        operator.setStatus(OperatorVerificationStatus.Status.NOTVERIFIED);
        operator.setUserId(userId);
        operator.setToken(generateToken());
        repository.create(mapToOperatorDb(operator));
        return operator;
    }

    public OperatorVerificationDb deleteOperatorVerificationStatus(Long userId){
        OperatorVerificationDb operatorVerificationDb = repository.getOperatorByUserID(userId);
        repository.delete(operatorVerificationDb.getId());
        return operatorVerificationDb;
    }

    public String updateOperatorVerificationToken(){
        OperatorVerificationDb operatorVerificationDb = repository.getOperatorByUserID(securityService.getCurrentUser().getId());
        operatorVerificationDb.setToken(generateToken());
        repository.update(operatorVerificationDb);
        return operatorVerificationDb.getToken();
    }

    public String generateToken(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public OperatorVerificationStatus.Status getOperatorStatus(){
        Long id = securityService.getCurrentUser().getId();
        OperatorVerificationDb operatorVerificationDb = repository.getOperatorByUserID(id);
        return operatorVerificationDb.getStatus();
    }

    private static OperatorVerification mapToOperator(OperatorVerificationDb db) {
        OperatorVerification api = new OperatorVerification();
        api.setId(db.getId());
        api.setUserId(db.getUserId());
        api.setToken(db.getToken());
        api.setStatus(OperatorVerificationStatus.Status.valueOf(db.getStatus().toString()));



        return api;
    }

    private static OperatorVerificationDb mapToOperatorDb(Long id, OperatorVerification api) {
        OperatorVerificationDb db = new OperatorVerificationDb();
        db.setId(id);
        db.setToken(api.getToken());
        db.setUserId(api.getUserId());
        db.setStatus(OperatorVerificationStatus.Status.valueOf(api.getStatus().toString()));
        return db;
    }



    private static OperatorVerificationDb mapToOperatorDb(OperatorVerification api) {
        return mapToOperatorDb(api.getId(), api);
    }




}
