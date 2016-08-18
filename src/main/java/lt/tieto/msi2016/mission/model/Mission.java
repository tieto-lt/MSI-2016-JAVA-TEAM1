package lt.tieto.msi2016.mission.model;

import java.util.List;

public class Mission {

    private String missionId;
    private Long submittedBy;
    private List<MissionCommand> commands;

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public Long getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Long submittedBy) {
        this.submittedBy = submittedBy;
    }

    public List<MissionCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<MissionCommand> commands) {
        this.commands = commands;
    }
}