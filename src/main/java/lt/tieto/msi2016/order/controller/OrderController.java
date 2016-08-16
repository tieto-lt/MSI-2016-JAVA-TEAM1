package lt.tieto.msi2016.order.controller;

import lt.tieto.msi2016.order.model.Order;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.order.service.OrderService;
import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService service;

    @RequestMapping(method = RequestMethod.POST, path = "/api/order")
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @Secured(Roles.ADMIN)
    @RequestMapping(method = RequestMethod.PUT, path = "/api/order/accept/{id}")
    public Order acceptOrder(@PathVariable Long id) {
        return service.updateOrderStatus(id, OrderDb.OrderState.Accepted);
    }

    @Secured(Roles.ADMIN)
    @RequestMapping(method = RequestMethod.PUT, path = "/api/order/decline/{id}")
    public Order declineOrder(@PathVariable Long id) {
        return service.updateOrderStatus(id, OrderDb.OrderState.Declined);
    }


}
