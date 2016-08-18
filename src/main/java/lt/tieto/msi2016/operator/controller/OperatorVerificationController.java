package lt.tieto.msi2016.operator.controller;

import lt.tieto.msi2016.operator.OperatorVerificationStatus;
import lt.tieto.msi2016.operator.model.OperatorVerificationToken;
import lt.tieto.msi2016.operator.services.OperatorVerificationService;
import lt.tieto.msi2016.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OperatorVerificationController {

    @Autowired
    private OperatorVerificationService service;

    @Secured(Roles.OPERATOR)
    @RequestMapping(method = RequestMethod.POST, path = "/api/token")
    public OperatorVerificationToken generateToken() {
        OperatorVerificationToken token = new OperatorVerificationToken();
        token.setToken(service.updateOperatorVerificationToken());
        return token;
    }

    @Secured(Roles.OPERATOR)
    @RequestMapping(method = RequestMethod.GET, path = "/api/operator")
    public OperatorVerificationStatus.Status getOperatorStatus() {
        return service.getOperatorStatus();
    }
}

