package lt.tieto.msi2016.mission.controller.operator;

import lt.tieto.msi2016.mission.model.operator.Mission;
import lt.tieto.msi2016.mission.model.operator.MissionCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MissionsHolder {

    private static List<Mission> mockMissions = new ArrayList<>();

    static {
        mockMissions.add(mission("0-simple", command("takeoff"), command("hover", 2000),  command("land")));
        mockMissions.add(mission("1-verification-mission", command("takeoff"), command("hover", 2000), command("takePicture"), command("cw", 180),  command("hover", 1000), command("takePicture"),  command("hover", 1000),  command("land")));
        mockMissions.add(mission("2-pictures-mission", command("takeoff"), command("hover", 2000), command("takePicture"), command("cw", 90), command("hover", 2000), command("takePicture"), command("cw", 90), command("hover", 2000), command("takePicture"), command("cw", 90), command("hover", 2000), command("takePicture"), command("cw", 90), command("hover", 2000), command("land")));
        /*mockMissions.add(mission("2-picture", command("wait", 1000), command("takePicture"), command("wait", 1000)));
        mockMissions.add(mission("2-picture", command("wait", 1000), command("takePicture"), command("wait", 1000)));
        mockMissions.add(mission("6-forw-back", command("takeoff"), command("forward", 1),
                command("hover", 1000), command("backward", 1), command("land")));
        mockMissions.add(mission("13-crash", command("takeoff"), command("altitude", 2), command("verticalCamera"), command("hover", 10000), command("takePicture"), command("forward", 3),
                command("hover", 2000), command("backward", 3), command("land")));*/
    }

    public static Optional<Mission> removeMission(String id) {
        Optional<Mission> mission = mockMissions.stream()
                .filter(m -> m.getMissionId().equals(id))
                .findAny();
        mission.ifPresent(mockMissions::remove);
        return mission;
    }

    public static List<Mission> getMissions() {
        return mockMissions;
    }



    private static Mission mission(String missionId, String... commands) {
        List<MissionCommand> missionCommands = new ArrayList<>();
        Stream.of(commands).forEach(c -> missionCommands.add(command(c)));
        Mission mission = new Mission();
        mission.setMissionId(missionId);
        mission.setCommands(missionCommands);
        return mission;
    }

    private static Mission mission(String missionId, MissionCommand... commands) {
        Mission mission = new Mission();
        mission.setMissionId(missionId);
        mission.setCommands(Arrays.asList(commands));
        return mission;
    }

    private static MissionCommand command(String commandType) {
        MissionCommand missionCommand = new MissionCommand();
        missionCommand.setCommandType(commandType);
        return missionCommand;
    }

    private static MissionCommand command(String commandType, Object... args) {
        MissionCommand missionCommand = new MissionCommand();
        missionCommand.setCommandType(commandType);
        missionCommand.setArgs(Arrays.asList(args));
        return missionCommand;
    }
}