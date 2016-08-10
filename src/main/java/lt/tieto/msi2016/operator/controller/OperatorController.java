package lt.tieto.msi2016.operator.controller;

import lt.tieto.msi2016.operator.model.TokenModel;
import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.operator.model.OperatorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lt.tieto.msi2016.operator.services.OperatorService;


@RestController
public class OperatorController {

    @Autowired
    private OperatorService service;

    @Secured(Roles.OPERATOR)
    @RequestMapping(method = RequestMethod.POST, path = "/api/token")
    public TokenModel generateToken() {
        TokenModel token = new TokenModel();
        token.setToken(service.generateToken());
        return token;
    }

    @Secured(Roles.OPERATOR)
    @RequestMapping(method = RequestMethod.GET, path = "/api/operator")
    public OperatorModel getOperatorStatus() {
        return service.getOperatorStatus();
    }
}

