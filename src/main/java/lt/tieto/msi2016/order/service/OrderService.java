package lt.tieto.msi2016.order.service;

import lt.tieto.msi2016.order.model.OrderModel;
import lt.tieto.msi2016.order.repository.OrdersRepository;
import lt.tieto.msi2016.order.repository.model.OrdersDb;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository repository;

    @Autowired
    private SecurityService securityService;

    public OrderModel createOrder(OrderModel order) {

        Long userId = securityService.getCurrentUser().getId();

        return mapToOrders(repository.create(mapToOrdersDb(order, userId)));

    }


    private static OrderModel mapToOrders(OrdersDb db) {
        OrderModel api = new OrderModel();
        api.setMissionName(db.getMissionName());
        api.setFullName(db.getFullName());
        api.setPhone(db.getPhone());
        api.setEmail(db.getEmail());
        api.setDetails(db.getDetails());
        return api;
    }

    private static OrdersDb mapToOrdersDb(Long id, OrderModel api, Long userId) {
        OrdersDb db = new OrdersDb();
        db.setId(id);
        db.setCreatedBy(userId);
        db.setMissionName(api.getMissionName());
        db.setFullName(api.getFullName());
        db.setPhone(api.getPhone());
        db.setEmail(api.getEmail());
        db.setSubmissionDate(DateTime.now());
        db.setDetails(api.getDetails());
        db.setOrderState(OrdersDb.OrderState.Pending);
        return db;
    }

    private static OrdersDb mapToOrdersDb(OrderModel api, Long userId) {
        return mapToOrdersDb(null, api, userId);
    }
}