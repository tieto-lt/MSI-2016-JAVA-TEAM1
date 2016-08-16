package lt.tieto.msi2016.order.service;

import lt.tieto.msi2016.order.model.Order;
import lt.tieto.msi2016.order.repository.OrderRepository;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private SecurityService securityService;

    public Order createOrder(Order order) {

        Long userId = securityService.getCurrentUser().getId();

        return mapToOrders(repository.create(mapToOrdersDb(order, userId)));

    }


    private static Order mapToOrders(OrderDb db) {
        Order api = new Order();
        api.setMissionName(db.getMissionName());
        api.setFullName(db.getFullName());
        api.setPhone(db.getPhone());
        api.setEmail(db.getEmail());
        api.setDetails(db.getDetails());
        return api;
    }

    private static OrderDb mapToOrdersDb(Long id, Order api, Long userId) {
        OrderDb db = new OrderDb();
        db.setId(id);
        db.setCreatedBy(userId);
        db.setMissionName(api.getMissionName());
        db.setFullName(api.getFullName());
        db.setPhone(api.getPhone());
        db.setEmail(api.getEmail());
        db.setSubmissionDate(DateTime.now());
        db.setDetails(api.getDetails());
        db.setOrderState(OrderDb.OrderState.Pending);
        return db;
    }

    private static OrderDb mapToOrdersDb(Order api, Long userId) {
        return mapToOrdersDb(null, api, userId);
    }
}