package service;

import model.Team;
import model.Hero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TeamService {

    private final List<Team> teams = new ArrayList<>();

    public Team addTeam(String name) {
        Team team = new Team(name);
        teams.add(team);
        return team;
    }

    public boolean removeTeam(UUID id) {
        Team team = findById(id);
        if (team == null) {
            return false;
        }
        teams.remove(team);
        return true;
    }

    public Team findById(UUID id) {
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }

    public List<Team> getTeams() {
        return Collections.unmodifiableList(teams);
    }

    public List<String> getTeamsToString() {
        List<String> result = new ArrayList<>();
        for (Team team : teams) {
            result.add(team.toString());
        }
        return result;
    }
    public boolean addHeroATeam(UUID teamId, Hero hero) {
        Team team = findById(teamId);
        if (team == null || hero == null) {
            return false;
        }
        return team.addHero(hero);
    }

    public boolean removeHeroDeTeam(UUID teamId, UUID heroId) {
        Team team = findById(teamId);
        if (team == null) {
            return false;
        }
        return team.removeHero(heroId);
    }
}