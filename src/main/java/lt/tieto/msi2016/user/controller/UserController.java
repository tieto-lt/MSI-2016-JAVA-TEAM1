package lt.tieto.msi2016.user.controller;

import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.user.model.User;
import lt.tieto.msi2016.user.service.UserService;
import lt.tieto.msi2016.utils.controller.BaseController;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController  extends BaseController {

    @Autowired
    private UserService service;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.POST, path = "/api/user")
    public User createUser(@RequestBody @Valid User user) {
        service.createUser(user);
        return user;
    }

    @Secured(Roles.CUSTOMER)
    @RequestMapping(method = RequestMethod.GET, path = "/api/user/current")
    public User getUserData() {
        return securityService.getCurrentUser();
    }
}