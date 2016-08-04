package lt.tieto.msi2016.user.controller;

import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.service.ItemService;
import lt.tieto.msi2016.user.model.User;
import lt.tieto.msi2016.user.service.UserService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by it11 on 16.8.3.
 */


@RestController
public class UserController  extends BaseController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.POST, path = "/api/user")
    public User createUser(@RequestBody User user) {
        service.createUser(user);
        return user;
    }



}