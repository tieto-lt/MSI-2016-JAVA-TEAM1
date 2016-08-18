package lt.tieto.msi2016.order.controller;

import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.order.model.Order;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.order.service.OrderService;
import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService service;

    @Secured(Roles.ADMIN)
    @RequestMapping(method = RequestMethod.GET, path = "/api/order")
    public List<Order> all() {return service.all();}

    @RequestMapping(method = RequestMethod.POST, path = "/api/order")
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @Secured(Roles.ADMIN)
    @RequestMapping(method = RequestMethod.PUT, path = "/api/order/accept/{id}")
    public Order acceptOrder(@PathVariable Long id) {
        return service.updateStatus(id, OrderDb.Status.Accepted);
    }

    @Secured(Roles.ADMIN)
    @RequestMapping(method = RequestMethod.PUT, path = "/api/order/decline/{id}")
    public Order declineOrder(@PathVariable Long id) {
        return service.updateStatus(id, OrderDb.Status.Declined);
    }

    @Secured(Roles.OPERATOR)
    @RequestMapping(method = RequestMethod.PUT, path = "/api/order/publish/{id}")
    public Order publishOrder(@PathVariable Long id) {
        return service.updateStatus(id, OrderDb.Status.Completed);
    }

    @Secured(Roles.OPERATOR)
    @RequestMapping(method = RequestMethod.PUT, path = "/api/order/redo/{id}")
    public Order redoOrder(@PathVariable Long id) {
        return service.updateStatus(id, OrderDb.Status.Accepted);
    }

}
