package lt.tieto.msi2016.order.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.repository.model.ItemDb;
import lt.tieto.msi2016.mission.model.Mission;
import lt.tieto.msi2016.mission.model.MissionCommand;
import lt.tieto.msi2016.mission.model.MissionImage;
import lt.tieto.msi2016.order.model.MapItems;
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

    @Transactional(readOnly = true)
    public Order get(Long id) throws IOException {
        OrderDb orderDb = repository.findOne(id);
        if (orderDb != null) {
            return mapToOrders(orderDb);
        } else {
            throw new DataNotFoundException("Item with id " + id + " not found");
        }
    }

    @Transactional
    public Order createOrder(Order order) throws IOException {
        Long userId = securityService.getCurrentUser().getId();
        OrderDb orderDb = repository.create(mapToOrdersDb(order, userId));
        orderDb.setMissionId(orderDb.getId() + "-" + order.getMissionName());
        return mapToOrders(repository.update(orderDb));

    }

    @Transactional(readOnly = true)
    public List<Order> all() throws IOException {
        List<Order> resultList = new ArrayList();
        for (OrderDb orderDb : repository.getOrdersSortedByStatus()) {
            resultList.add(mapToOrders(orderDb));
        }
        return resultList;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllUserOrders() throws IOException {
        List<Order> resultList = new ArrayList<>();
        Long userId = securityService.getCurrentUser().getId();
        for (OrderDb orderDb: repository.getOrdersByUserId(userId)) {
            resultList.add(mapToOrders(orderDb));
        }
        return  resultList;
    }

    @Transactional
    public Order updateStatus(Long id, OrderDb.Status status) throws IOException {
        OrderDb orderDb = repository.findOne(id);
        if (orderDb != null) {
            orderDb.setStatus(status);
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
        return repository.getOrdersByStatus(OrderDb.Status.Accepted);
    }


    private Order mapToOrders(OrderDb db) throws IOException {
        Order api = new Order();
        api.setId(db.getId());
        api.setMissionName(db.getMissionId().split("-")[1]);
        api.setFullName(db.getFullName());
        api.setPhone(db.getPhone());
        api.setEmail(db.getEmail());
        api.setDetails(db.getDetails());
        api.setStatus(db.getStatus());
        api.setSubmissionDate(db.getSubmissionDate());
        api.setMissionCommands(objectMapper.readValue(db.getCommands(), new TypeReference<List<MissionCommand>>() {}));
        if (db.getMapItems() != null) {
            api.setMapItems(objectMapper.readValue(db.getMapItems(), new TypeReference<List<MapItems>>(){}));
        }
        return api;
    }

    private OrderDb mapToOrdersDb(Long id, Order api, Long userId) throws JsonProcessingException {
        OrderDb db = new OrderDb();
        db.setId(id);
        db.setSubmittedBy(userId);
        db.setMissionId(api.getMissionName()); // on order creation append order id to this ({order_id}-{mission_name})
        db.setSubmissionDate(DateTime.now());
        db.setDetails(api.getDetails());
        db.setStatus(OrderDb.Status.Pending);
        db.setCommands(objectMapper.writeValueAsString(api.getMissionCommands()));
        db.setMapItems(objectMapper.writeValueAsString(api.getMapItems()));
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

    private OrderDb mapToOrdersDb(Order api, Long userId) throws JsonProcessingException {
        return mapToOrdersDb(null, api, userId);
    }
}