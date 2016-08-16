package lt.tieto.msi2016.order.controller;

import lt.tieto.msi2016.order.model.Order;
import lt.tieto.msi2016.order.service.OrderService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService service;

    @RequestMapping(method = RequestMethod.POST, path = "/api/order")
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }
}
