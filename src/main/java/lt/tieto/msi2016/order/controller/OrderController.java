package lt.tieto.msi2016.order.controller;

import lt.tieto.msi2016.order.model.OrderModel;
import lt.tieto.msi2016.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping(method = RequestMethod.POST, path = "/api/order")
    public OrderModel createOrder(OrderModel order) {
        return service.createOrder(order);
    }
}
