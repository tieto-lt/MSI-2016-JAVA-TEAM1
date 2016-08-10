package lt.tieto.msi2016.mission.controller;


import lt.tieto.msi2016.mission.model.MissionResultUI;
import lt.tieto.msi2016.mission.service.MissionResultsService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MissionResultsController extends BaseController {

    @Autowired
    private MissionResultsService service;

    @RequestMapping(method = RequestMethod.GET, path = "/api/internalMissions/{id}")
    public MissionResultUI get(@PathVariable Long id) throws IOException {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/internalMissions")
    public List<MissionResultUI> all() throws IOException {
        return service.all();
    }

}