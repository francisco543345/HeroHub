package controller;

import service.FanService;
import service.HeroService;
import service.MissionService;
import service.TeamService;
import model.Team;
import model.Hero;
import java.util.List;
import java.util.UUID;
public class AdminController {

    private final HeroService heroService;
    private final FanService fanService;
    private final TeamService teamService;
    private final MissionService missionService;

    public AdminController(HeroService heroService,
                           FanService fanService,
                           TeamService teamService,
                           MissionService missionService) {
        this.heroService = heroService;
        this.fanService = fanService;
        this.teamService = teamService;
        this.missionService = missionService;
    }

    public void addFan(String username, String password, String name, String lastName, int age) {
        fanService.addFan(username, password, name, lastName, age);
    }

    public boolean removeFan(String id) {
        UUID uuid = UUID.fromString(id);
        return fanService.removeFan(uuid);
    }

    public List<String> getFansToString() {
        return fanService.getFansToString();
    }

    public void addMission(String codeName, int threatLevel, int durationInHours, String city) {
        missionService.addMission(codeName, threatLevel, durationInHours, city);
    }

    public boolean removeMission(String id) {
        UUID uuid = UUID.fromString(id);
        return missionService.removeMission(uuid);
    }

    public List<String> getMissionesToString() {
        return missionService.getMissionesToString();
    }

    public void addHero(String name, String power, String originCity,
                        int combat, int intellect, int vigor, int charisma, int mobility,
                        int experience) {
        heroService.addHero(name, power, originCity, combat, intellect, vigor, charisma, mobility, experience);
    }

    public boolean removeHero(String id) {
        UUID uuid = UUID.fromString(id);
        return heroService.removeHero(uuid);
    }

    public List<String> getHeroesToString() {
        return heroService.getHeroesToString();
    }

    public void addTeam(String name) {
        teamService.addTeam(name);
    }

    public boolean removeTeam(String id) {
        UUID uuid = UUID.fromString(id);
        return teamService.removeTeam(uuid);
    }

    public List<String> getTeamsToString() {
        return teamService.getTeamsToString();
    }

    public boolean addHeroATeam(String heroId, String teamId) {
        UUID hId = UUID.fromString(heroId);
        UUID tId = UUID.fromString(teamId);
        Hero hero = heroService.findById(hId);
        if (hero == null) {
            return false;
        }
        return teamService.addHeroATeam(tId, hero);
    }

    public boolean removeHeroDeTeam(String heroId, String teamId) {
        UUID hId = UUID.fromString(heroId);
        UUID tId = UUID.fromString(teamId);
        return teamService.removeHeroDeTeam(tId, hId);
    }
    public boolean addTeamAMission(String teamId, String missionId) {
        UUID tId = UUID.fromString(teamId);
        UUID mId = UUID.fromString(missionId);
        Team team = teamService.findById(tId);
        if (team == null) {
            return false;
        }
        return missionService.addTeamAMission(mId, team);
    }

    public boolean removeTeamDeMission(String missionId) {
        UUID mId = UUID.fromString(missionId);
        return missionService.removeTeamDeMission(mId);
    }

}