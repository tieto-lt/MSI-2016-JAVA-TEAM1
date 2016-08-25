package lt.tieto.msi2016.mission.model;

import java.util.Arrays;
import java.util.List;

public class MissionCommand {

    private String commandType;
    private List args;

    public MissionCommand(){
    }

    public MissionCommand(String commandType){
        this.commandType = commandType;
    }

    public MissionCommand(String commandType, Object... args){
        this.commandType = commandType;
        this.args = Arrays.asList(args);
    }

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
