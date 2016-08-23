package lt.tieto.msi2016.order.controller;


import lt.tieto.msi2016.mission.model.MissionImage;
import lt.tieto.msi2016.mission.model.MissionNavigationData;
import lt.tieto.msi2016.order.model.OrderResults;
import lt.tieto.msi2016.order.service.OrderResultsService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderResultsController extends BaseController {

    @Autowired
    private OrderResultsService service;

    @RequestMapping(method = RequestMethod.GET, path = "/api/missionsUI/{id}")
    public OrderResults get(@PathVariable Long id) throws IOException {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/missionsUI")
    public List<OrderResults> all() throws IOException {
      /*  OrderResults order = new OrderResults();
        order.setBatteryStatus(new BigDecimal("60"));
        order.setExecutionDate(new DateTime("50"));
        order.setFinishNavigationData(new MissionNavigationData());
        order.setStartNavigationData(new MissionNavigationData());
        order.setMissionName("Mission name");
        order.setMissionId(new Long("50"));
        MissionImage img = new MissionImage();
        List a = new ArrayList<MissionImage>();
        a.add(img);
        order.setImages(a);

        List list = new ArrayList<OrderResults>();
        list.add(order);
        return list;*/
        return service.all();
    }
}