package lt.tieto.msi2016.roles.controller;

import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Secured("ROLE_ADMIN")
@RestController
public class RolesController extends BaseController {

    @RequestMapping(method = RequestMethod.GET, path = "/api/roles")
    public String all() {

        return new String("[\n" +
                "  {\n" +
                "    \"id\": 777,\n" +
                "    \"name\": \"RolesVeikia\",\n" +
                "    \"quantity\": 47,\n" +
                "    \"size\": \"100x100\"\n" +
                "  }\n" +
                "]");
    }

}