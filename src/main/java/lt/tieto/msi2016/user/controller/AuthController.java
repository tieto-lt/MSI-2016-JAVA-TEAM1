package lt.tieto.msi2016.user.controller;

import lt.tieto.msi2016.user.model.User;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by localadmin on 16.8.8.
 */
@RestController
public class AuthController {

    @Autowired
    SecurityService securityService;

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/logout")
    public void logout() {
        securityService.removeToken();
    }
}
