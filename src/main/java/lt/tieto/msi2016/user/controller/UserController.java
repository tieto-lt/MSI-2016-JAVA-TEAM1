package lt.tieto.msi2016.user.controller;

import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.user.model.User;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * Created by it11 on 16.8.3.
 */


@RestController
public class UserController  extends BaseController {



    @RequestMapping(method = RequestMethod.POST, path = "/api/user")
    public User createItem(@RequestBody User user) {
        System.out.println(user);

        return user;
    }




    @Secured(Roles.CUSTOMER)
    @RequestMapping(method = RequestMethod.GET, path = "/api/user/{id}")
    public User getUserData(@PathVariable Long id) {
        return id;
    }






}