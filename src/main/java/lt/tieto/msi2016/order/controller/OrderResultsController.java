package lt.tieto.msi2016.order.controller;


import lt.tieto.msi2016.order.model.OrderResults;
import lt.tieto.msi2016.order.service.OrderResultsService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OrderResultsController extends BaseController {

    @Autowired
    private OrderResultsService service;

    @RequestMapping(method = RequestMethod.GET, path = "/api/missionsUI/{id}")
    public OrderResults get(@PathVariable Long id) throws IOException {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/missionsUI")
    public List<OrderResults> all() throws IOException {
      return service.all();
    }
}