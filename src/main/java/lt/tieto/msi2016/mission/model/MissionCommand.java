package lt.tieto.msi2016.mission.model;

import java.util.List;

public class MissionCommand {

    private String commandType;
    private List args;

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public List getArgs() {
        return args;
    }

    public void setArgs(List args) {
        this.args = args;
    }
}
