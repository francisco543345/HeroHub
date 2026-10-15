package service;

import model.Mission;
import model.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MissionService {
    private final List<Mission> missiones = new ArrayList<>();
    public Mission addMission(String codeName, int threatLevel, int durationInHours, String city ) {
        Mission mission = new Mission(codeName, threatLevel, durationInHours, city);
        missiones.add(mission);
        return mission;
    }

    public boolean removeMission(UUID id) {
        Mission mission = findById(id);

        if (mission == null) {
            return false;
        }
        missiones.remove(mission);
        return true;

    }

    // Devuelve el fanático con ese id, o null si no existe.
    public Mission findById(UUID id) {
        for (Mission mission : missiones) {
            if (mission.getId().equals(id)) {
                return mission;
            }
        }
        return null;
    }

    public List<Mission> getMissiones() {
        return Collections.unmodifiableList(missiones);
    }

    public List<String> getMissionesToString() {
        List<String> result = new ArrayList<>();
        for (Mission mission : missiones) {
            result.add(mission.toString());
        }
        return result;
    }

    public boolean addTeamAMission(UUID missionId, Team team) {
        Mission mission = findById(missionId);
        if (mission == null || team == null) {
            return false;
        }
        return mission.assignTeam(team);
    }

    public boolean removeTeamDeMission(UUID missionId) {
        Mission mission = findById(missionId);
        if (mission == null) {
            return false;
        }
        return mission.removeTeam();
    }

}
