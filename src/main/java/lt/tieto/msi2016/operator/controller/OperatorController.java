package lt.tieto.msi2016.operator.controller;

import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.roles.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by it11 on 16.8.4.
 */
@RestController
public class OperatorController {

    @Secured(Roles.OPERATOR)
    @RequestMapping(method = RequestMethod.GET, path = "/api/operator")

    public String all() {

        return new String("test");
    }


}

