package model;

import java.util.UUID;

public class Mission {

    private UUID id;
    private String codeName;
    private int threatLevel;
    private int durationInHours;
    private String city;
    private Team team;

    public Mission(String codeName, int threatLevel, int durationInHours, String city) {
        this.id = UUID.randomUUID();
        this.codeName = codeName;
        this.threatLevel = threatLevel;
        this.durationInHours = durationInHours;
        this.city = city;
        this.team = null;
    }

    public boolean assignTeam(Team team) {
        if (team == null) {
            return false;
        }
        this.team = team;
        return true;
    }

    public boolean removeTeam() {
        if (this.team == null) {
            return false;
        }
        this.team = null;
        return true;
    }

    public Team getTeam() {
        return team;
    }

    public UUID getId() {
        return id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public int getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(int threatLevel) {
        this.threatLevel = threatLevel;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        String teamInfo = (team != null) ? team.getName() : "Sin equipo";
        return "id: " + id + " - codeName: " + codeName + " - threatLevel: " + threatLevel
                + " - durationInHours: " + durationInHours + " - city: " + city
                + " - team: " + teamInfo;
    }
}
