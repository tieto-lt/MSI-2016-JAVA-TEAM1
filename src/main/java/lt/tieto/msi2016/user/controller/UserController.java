package lt.tieto.msi2016.user.controller;

import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.user.model.Password;
import lt.tieto.msi2016.user.model.PaymentUrl;
import lt.tieto.msi2016.user.model.User;
import lt.tieto.msi2016.user.service.PaymentService;
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

    @Autowired
    private PaymentService paymentService;

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

    @RequestMapping(method = RequestMethod.POST, path = "/api/user/information")
    public User updateInformation(@RequestBody @Valid User user) {
        return service.updateUserInformation(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/customer/password")
    public Password changeUserPassword(@RequestBody @Valid Password password) {
        return service.changeUserPassword(password);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/customer/balance/{id}")
    public Long getBalance(@PathVariable Long id){

        return new Long("15");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/customer/deposit")
    public PaymentUrl getPayUrl(@RequestParam("amount") Integer amount){
        System.out.println("getPayUrl amount received");
        PaymentUrl paymentUrl = new PaymentUrl();
        paymentUrl.setUrl(paymentService.getEncodedUrl());
        return paymentUrl;
    }

}