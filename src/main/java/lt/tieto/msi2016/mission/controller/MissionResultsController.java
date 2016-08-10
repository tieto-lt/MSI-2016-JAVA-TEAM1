package lt.tieto.msi2016.mission.controller;


import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.service.ItemService;
import lt.tieto.msi2016.mission.model.MissionResultInternal;
import lt.tieto.msi2016.mission.service.MissionService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MissionResultsController extends BaseController {

    @Autowired
    private MissionService service;

    @RequestMapping(method = RequestMethod.GET, path = "/api/internalMissions/{id}")
    public MissionResultInternal get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/internalMissions")
    public List<MissionResultInternal> all() {
        return service.all();
    }

}