package lt.tieto.msi2016.mission.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lt.tieto.msi2016.mission.model.MissionResultUI;
import lt.tieto.msi2016.mission.model.operator.MissionImage;
import lt.tieto.msi2016.mission.model.operator.MissionNavigationData;
import lt.tieto.msi2016.mission.model.operator.MissionResult;
import lt.tieto.msi2016.mission.repository.MissionResultsRepository;
import lt.tieto.msi2016.mission.repository.model.MissionResultsDb;
import lt.tieto.msi2016.operator.services.OperatorService;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MissionResultsService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MissionResultsRepository repository;

    @Transactional(readOnly = true)
    public MissionResultUI get(Long id) throws IOException {
        MissionResultsDb missionResultsDb = repository.findOne(id);
        if (missionResultsDb != null) {
            return mapToMissionResultUI(missionResultsDb);
        } else {
            throw new DataNotFoundException("Mission results with id " + id + " not found");
        }
    }

    @Transactional(readOnly = true)
    public List<MissionResultUI> all() throws IOException {
        List<MissionResultUI> resultList = new ArrayList();
        for (MissionResultsDb missionResultsDb : repository.findAll()) {
            resultList.add(mapToMissionResultUI(missionResultsDb));
        }
        return resultList;
    }

    @Transactional
    public MissionResultUI saveMissionResult(MissionResult missionResult) throws IOException {

        return mapToMissionResultUI(repository.create(mapToMissionResultsDb(missionResult)));

    }

    private MissionResultUI mapToMissionResultUI(MissionResultsDb db) throws IOException {
        List<MissionNavigationData> navigationData = objectMapper.readValue(db.getNavigationData(), new TypeReference<List<MissionNavigationData>>() {});
        MissionResultUI api = new MissionResultUI();
        api.setId(db.getId());
        api.setStartNavigationData(navigationData != null ? navigationData.get(0) : null);
        api.setFinishNavigationData(navigationData != null ? navigationData.get(navigationData.size() - 1) : null);
        api.setImages(objectMapper.readValue(db.getImages(), new TypeReference<List<MissionImage>>() {}));
        api.setBatteryStatus(navigationData != null ? navigationData.get(navigationData.size() - 1).getBattery() : null);
        return api;
    }

    private MissionResultsDb mapToMissionResultsDb(MissionResult api) throws IOException {
        MissionResultsDb db = new MissionResultsDb();
        db.setMissionId(null); // only for test missions
        //db.setOrderId();
        //db.setExecutedBy();
        db.setExecutionDate(new DateTime());
        db.setBatteryStatus(api.getNavigationData().get(api.getNavigationData().size() - 1).battery);
        db.setVideoBase64(api.getVideoBase64());
        db.setImages(objectMapper.writeValueAsString(api.getImages()));
        db.setNavigationData(objectMapper.writeValueAsString(api.getNavigationData()));
        db.setMissionState(MissionResultsDb.MissionState.Completed);
        return db;
    }
}
