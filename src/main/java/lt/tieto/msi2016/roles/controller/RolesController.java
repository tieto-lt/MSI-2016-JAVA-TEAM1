package lt.tieto.msi2016.roles.controller;

import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.roles.model.Role;
import lt.tieto.msi2016.roles.service.RoleService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Secured(Roles.ADMIN)
@RestController
public class RolesController extends BaseController {

    @Autowired
    RoleService service;

    @RequestMapping(method = RequestMethod.GET, path = "/api/roles")
    public List<Role> all() {
        return service.all();
    }

}