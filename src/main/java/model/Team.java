package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Team {

    private UUID id;
    private String name;
    private List<Hero> heroes;

    public Team(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.heroes = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hero> getHeroes() {
        return Collections.unmodifiableList(heroes);
    }

    public boolean addHero(Hero hero) {
        if (hero == null) {
            return false;
        }
        for (Hero h : heroes) {
            if (h.getId().equals(hero.getId())) {
                return false;
            }
        }
        heroes.add(hero);
        return true;
    }

    public boolean removeHero(UUID heroId) {
        for (Hero h : heroes) {
            if (h.getId().equals(heroId)) {
                heroes.remove(h);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "id: " + id + " - name: " + name + " - heroes: " + heroes.size();
    }
}