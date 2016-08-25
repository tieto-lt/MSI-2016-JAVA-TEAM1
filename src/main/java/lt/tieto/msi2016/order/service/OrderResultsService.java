package lt.tieto.msi2016.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.tieto.msi2016.mission.model.MissionImage;
import lt.tieto.msi2016.mission.model.MissionNavigationData;
import lt.tieto.msi2016.mission.model.MissionResult;
import lt.tieto.msi2016.order.model.OrderResults;
import lt.tieto.msi2016.order.repository.OrderResultsRepository;
import lt.tieto.msi2016.order.repository.model.OrderResultsDb;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class OrderResultsService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VideoConverterConnector videoConverterConnector;

    @Autowired
    private OrderResultsRepository repository;

    @Autowired
    private SecurityService securityService;

    @Transactional(readOnly = true)
    public OrderResults get(Long id) throws IOException {
        OrderResultsDb orderResultsDb = repository.findOne(id);
        if (orderResultsDb != null) {
            return mapToOrderResults(orderResultsDb);
        } else {
            throw new DataNotFoundException("Mission results with id " + id + " not found");
        }
    }

    @Transactional(readOnly = true)
    public List<OrderResults> all() throws IOException {
        List<OrderResults> resultList = new ArrayList();
        for (OrderResultsDb orderResultsDb : repository.findAll()) {
            resultList.add(mapToOrderResults(orderResultsDb));
        }
        return resultList;
    }

    @Transactional(readOnly = true)
    public List<OrderResults> getOrderResultsByOrderId(Long id) throws IOException {
        List<OrderResults> resultList = new ArrayList();
        for (OrderResultsDb orderResultsDb : repository.getOrderResultsByOrderId(id)) {
            resultList.add(mapToOrderResults(orderResultsDb));
        }
        return resultList;
    }



    @Transactional(readOnly = true)
    public List<OrderResults> getOrderResultsByOPeratorID() throws IOException {
        List<OrderResults> resultList = new ArrayList();
        Long userId = securityService.getCurrentUser().getId();
        for (OrderResultsDb orderResultsDb : repository.getOrderResultsByOperatorId(userId)) {
            resultList.add(mapToOrderResults(orderResultsDb));
        }
        return resultList;
    }




    @Transactional(readOnly = true)
    public byte[] getOrderVideo(Long id) {
        OrderResultsDb orderResultsDb = repository.findOne(id);
        String base64Video = orderResultsDb.getVideoBase64();
        if (base64Video != null) {
            byte[] decoded = Base64.getDecoder().decode(base64Video);
            return videoConverterConnector.convertVideo(decoded);
        } else {
            return null;
        }
    }



    @Transactional
    public OrderResults saveMissionResult(MissionResult missionResult, Long executedBy) throws IOException {

        return mapToOrderResults(repository.create(mapToOrderResultsDb(missionResult, executedBy)));

    }

    private OrderResults mapToOrderResults(OrderResultsDb db) throws IOException {
        List<MissionNavigationData> navigationData = objectMapper.readValue(db.getNavigationData(), new TypeReference<List<MissionNavigationData>>() {});
        OrderResults api = new OrderResults();
        api.setId(db.getId());
        api.setMissionId(db.getOrderId()); // our missionId is {order_id}-{mission_name}
        api.setOrderId(db.getOrderId());
        api.setStartNavigationData(navigationData != null ? navigationData.get(0) : null);
        api.setFinishNavigationData(navigationData != null ? navigationData.get(navigationData.size() - 1) : null);
        api.setImages(objectMapper.readValue(db.getImages(), new TypeReference<List<MissionImage>>() {}));
        api.setBatteryStatus(navigationData != null ? navigationData.get(navigationData.size() - 1).getBattery() : null);
        api.setMissionName(db.getMissionName());
        api.setExecutionDate(db.getExecutionDate());


        return api;
    }

    private OrderResultsDb mapToOrderResultsDb(MissionResult api, Long executedBy) throws JsonProcessingException {
        OrderResultsDb db = new OrderResultsDb();
        db.setExecutedBy(executedBy);
        db.setOrderId(Long.valueOf(api.getMissionId().split("-")[0]));
        db.setMissionName(api.getMissionId().split("-")[1]);
        db.setExecutionDate(DateTime.now());
        db.setBatteryStatus(api.getNavigationData().get(api.getNavigationData().size() - 1).battery);
        db.setImages(objectMapper.writeValueAsString(api.getImages()));
        db.setVideoBase64(api.getVideoBase64());
        db.setNavigationData(objectMapper.writeValueAsString(api.getNavigationData()));

        return db;
    }

    @Transactional
    public void remove(Long id) {
        if (!repository.exists(id)) {
            throw new DataNotFoundException("Item with id " + id + " doesn't exist");
        }
        repository.delete(id);
    }
}
