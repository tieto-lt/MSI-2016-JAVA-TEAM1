package lt.tieto.msi2016.order.service;

import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.repository.model.ItemDb;
import lt.tieto.msi2016.item.service.ItemService;
import lt.tieto.msi2016.order.model.Order;
import lt.tieto.msi2016.order.repository.OrderRepository;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<Order> all() {
        return repository.findAll().stream()
                .map(OrderService::mapToOrders)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Order> allOrdersOfCustomer() {
        Long userId = securityService.getCurrentUser().getId();
        List <OrderDb> ordersOfCustomerDb = repository.getOrderByUserId(userId);
        List <Order> ordersOfCustomer = ordersOfCustomerDb.stream()
                .map(OrderService::mapToOrders)
                .collect(Collectors.toList());

        return  ordersOfCustomer;
    }



    @Transactional
    public Order updateOrderStatus(Long id, OrderDb.OrderState orderState) {
        OrderDb orderDb = repository.findOne(id);
        if (orderDb != null) {
            orderDb.setOrderState(orderState);
            OrderDb updated = repository.update(orderDb);
            return mapToOrders(updated);
        } else {
            throw new DataNotFoundException("There is no order with id = " + id);
        }
    }


    private static Order mapToOrders(OrderDb db) {
        Order api = new Order();
        api.setId(db.getId());
        api.setMissionName(db.getMissionName());
        api.setFullName(db.getFullName());
        api.setPhone(db.getPhone());
        api.setEmail(db.getEmail());
        api.setDetails(db.getDetails());
        api.setOrderState(db.getOrderState());
        api.setSubmissionDate(db.getSubmissionDate());
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