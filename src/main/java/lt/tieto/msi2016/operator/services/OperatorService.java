package lt.tieto.msi2016.operator.services;

import lt.tieto.msi2016.operator.model.OperatorModel;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OperatorService {

    @Autowired
    private SecurityService securityService;

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
}
