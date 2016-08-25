package lt.tieto.msi2016.transaction.controller;


import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.transaction.model.UserBalance;
import lt.tieto.msi2016.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Secured(Roles.CUSTOMER)
    @RequestMapping(method = RequestMethod.GET, path="/api/payment/")
    public UserBalance userBalance (){
        UserBalance userBalance = new UserBalance();
        userBalance.setBalance( service.getBalance());
        return userBalance;
    }





}
