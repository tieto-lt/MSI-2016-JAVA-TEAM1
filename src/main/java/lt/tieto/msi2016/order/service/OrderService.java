package lt.tieto.msi2016.order.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.tieto.msi2016.mission.model.Mission;
import lt.tieto.msi2016.mission.model.MissionCommand;
import lt.tieto.msi2016.order.model.Order;
import lt.tieto.msi2016.order.model.OrderResults;
import lt.tieto.msi2016.order.repository.OrderRepository;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.order.repository.model.OrderResultsDb;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ObjectMapper objectMapper;
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
        List<Order> resultList = new ArrayList();
        for (OrderDb orderDb : repository.findAll()) {
            resultList.add(mapToOrders(orderDb));
        }
        return resultList;
    }

    @Transactional
    public Order updateOrderStatus(Long id, OrderDb.OrderStatus orderStatus) {
        OrderDb orderDb = repository.findOne(id);
        if (orderDb != null) {
            orderDb.setOrderStatus(orderStatus);
            OrderDb updated = repository.update(orderDb);
            return mapToOrders(updated);
        } else {
            throw new DataNotFoundException("There is no order with id = " + id);
        }
    }

    @Transactional(readOnly = true)
    public List<Mission> getAllMissions() throws IOException {
        List<Mission> resultList = new ArrayList();
        for (OrderDb orderDb : getAllAcceptedOrders()) {
            resultList.add(mapToMission(orderDb));
        }
        return resultList;
    }

    private List<OrderDb> getAllAcceptedOrders() {
        return repository.getOrdersByStatus(OrderDb.OrderStatus.Accepted);
    }


    private Order mapToOrders(OrderDb db) {
        Order api = new Order();
        api.setId(db.getId());
        api.setMissionName(db.getMissionId().split("-")[0]);
        api.setFullName(db.getFullName());
        api.setPhone(db.getPhone());
        api.setEmail(db.getEmail());
        api.setDetails(db.getDetails());
        api.setOrderStatus(db.getOrderStatus());
        api.setSubmissionDate(db.getSubmissionDate());
        return api;
    }

    private OrderDb mapToOrdersDb(Long id, Order api, Long userId) {
        OrderDb db = new OrderDb();
        db.setId(id);
        db.setSubmittedBy(userId);
        db.setMissionId(api.getMissionName()); // on order creation append order id to this ({order_id}-{mission_name})
        db.setSubmissionDate(DateTime.now());
        db.setDetails(api.getDetails());
        db.setOrderStatus(OrderDb.OrderStatus.Pending);
        //db.setCommands();
        db.setFullName(api.getFullName());
        db.setEmail(api.getEmail());
        db.setPhone(api.getPhone());
        return db;
    }

    @Transactional(readOnly = true)
    public Mission getMissionByMissionId(String missionId) throws IOException {
        Long orderId = Long.valueOf(missionId.split("-")[0]);
        OrderDb orderDb = repository.findOne(orderId);
        if (orderDb != null) {
            return mapToMission(orderDb);
        } else {
            throw new DataNotFoundException("Order with id " + orderId + " not found");
        }
    }

    private Mission mapToMission(OrderDb db) throws IOException {
        Mission mission = new Mission();
        mission.setMissionId(db.getMissionId());
        mission.setSubmittedBy(db.getSubmittedBy());
        mission.setCommands(objectMapper.readValue(db.getCommands(), new TypeReference<List<MissionCommand>>() {}));
        return mission;
    }

    private OrderDb mapToOrdersDb(Order api, Long userId) {
        return mapToOrdersDb(null, api, userId);
    }
}