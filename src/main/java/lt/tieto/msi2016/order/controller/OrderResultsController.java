package lt.tieto.msi2016.order.controller;


import lt.tieto.msi2016.order.model.OrderResults;
import lt.tieto.msi2016.order.service.OrderResultsService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class OrderResultsController extends BaseController {

    public static final MediaType VIDEO_MEDIA_TYPE = new MediaType("video", "mp4");

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

    @RequestMapping(method = RequestMethod.GET, path = "/api/missionsUI/video/{id}", produces = "video/mp4")
    public HttpEntity<byte[]> getMissionVideo(@PathVariable Long id) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(VIDEO_MEDIA_TYPE);
        byte[] video = service.getOrderVideo(id);
        return new HttpEntity<>(video, httpHeaders);
    }

}