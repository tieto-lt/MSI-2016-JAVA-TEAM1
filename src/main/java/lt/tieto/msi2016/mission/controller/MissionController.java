package lt.tieto.msi2016.mission.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.tieto.msi2016.mission.model.Mission;
import lt.tieto.msi2016.mission.model.MissionResult;
import lt.tieto.msi2016.mission.model.Missions;

import lt.tieto.msi2016.operator.OperatorVerificationStatus;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import lt.tieto.msi2016.order.service.OrderResultsService;

import lt.tieto.msi2016.operator.services.OperatorVerificationService;
import lt.tieto.msi2016.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MissionController {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    private OrderResultsService orderResultsService;
    @Autowired
    private OperatorVerificationService operatorVerificationService;
    @Autowired
    private OrderService orderService;


    private static Logger LOG = LoggerFactory.getLogger(MissionController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/api/missions", params = "operatorToken")
    public Missions getMissions(@RequestParam String operatorToken) throws IOException {
        if (operatorVerificationService.isOperatorValidByToken(operatorToken)) {
           // System.out.println("Getting missions");
            Missions missions = new Missions();
            if (OperatorVerificationStatus.Status.VERIFIED == operatorVerificationService.getOperatorStatus(operatorToken)) {
                missions.setMissions(orderService.getAllMissions());
            } else {
                // only test mission for not verified operators
                missions.setMissions(MissionsHolder.getVerificationMission());
            }
            return missions;
        } else {
            throw new UnauthorizedUserException("Operator token is not valid");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/missions/{missionId}/reserve", params = "operatorToken")
    public Mission reserveMission(@PathVariable String missionId, @RequestParam String operatorToken) throws IOException  {
        if (operatorVerificationService.isOperatorValidByToken(operatorToken)) {
            LOG.info("reserve missions");
            if (OperatorVerificationStatus.Status.VERIFIED == operatorVerificationService.getOperatorStatus(operatorToken)) {
                orderService.updateStatus(Long.valueOf(missionId.split("-")[0]), OrderDb.Status.InProgress);
                return orderService.getMissionByMissionId(missionId);
            } else {
                return MissionsHolder.getVerificationMission().get(0);
            }
        } else {
            throw new UnauthorizedUserException("Operator token is not valid");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/missions/{missionId}", params = "operatorToken")
    public void completeMission(
            @PathVariable String missionId,
            @RequestBody MissionResult missionResult,
            @RequestParam String operatorToken) throws IOException {
        if (operatorVerificationService.isOperatorValidByToken(operatorToken)) {
            LOG.info("Completing mission {} {}", missionId, missionResult);
            if (OperatorVerificationStatus.Status.VERIFIED == operatorVerificationService.getOperatorStatus(operatorToken)) {
                orderResultsService.saveMissionResult(missionResult, operatorVerificationService.getOperatorByToken(operatorToken).getUserId());
                orderService.updateStatus(Long.valueOf(missionId.split("-")[0]), OrderDb.Status.Executed);
            } else {
                operatorVerificationService.verifyAndUpdateStatus(missionResult, operatorToken);
            }
        } else {
            throw new UnauthorizedUserException("Operator token is not valid");
        }
    }



}
