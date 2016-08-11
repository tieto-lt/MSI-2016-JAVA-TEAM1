package lt.tieto.msi2016.mission.controller.operator;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.tieto.msi2016.mission.controller.operator.MissionsHolder;
import lt.tieto.msi2016.mission.model.operator.Mission;
import lt.tieto.msi2016.mission.model.operator.MissionResult;
import lt.tieto.msi2016.mission.model.operator.Missions;

import lt.tieto.msi2016.mission.service.MissionResultsService;

import lt.tieto.msi2016.operator.services.OperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MissionController {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    private MissionResultsService missionResultsService;
    @Autowired
    private OperatorService operatorService;


    private static Logger LOG = LoggerFactory.getLogger(MissionController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/api/missions", params = "operatorToken")
    public Missions getMissions(@RequestParam String operatorToken) {
        if (operatorService.isOperatorValidByToken(operatorToken)) {
            System.out.println("Getting missions " + operatorToken);
            Missions missions = new Missions();
            missions.setMissions(MissionsHolder.getMissions());
            return missions;
        } else {
            throw new UnauthorizedUserException("Operator token is not valid");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/missions/{missionId}/reserve", params = "operatorToken")
    public Mission reserveMission(@PathVariable String missionId, @RequestParam String operatorToken) {
        if (operatorService.isOperatorValidByToken(operatorToken)) {
            LOG.info("reserve missions");
            LOG.info(operatorToken);
            return MissionsHolder.removeMission(missionId).get();
        } else {
            throw new UnauthorizedUserException("Operator token is not valid");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/missions/{missionId}", params = "operatorToken")
    public void completeMission(
            @PathVariable String missionId,
            @RequestBody MissionResult missionResult,
            @RequestParam String operatorToken) throws IOException {
        if (operatorService.isOperatorValidByToken(operatorToken)) {
            LOG.info("Completing mission {} {}", missionId, missionResult);
            missionResultsService.saveMissionResult(missionResult);
            operatorService.verifyAndUpdateStatus(missionResult, operatorToken);
        } else {
            throw new UnauthorizedUserException("Operator token is not valid");
        }
    }

}
