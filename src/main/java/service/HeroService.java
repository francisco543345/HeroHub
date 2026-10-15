package service;

import model.Hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class HeroService {

    private final List<Hero> heroes = new ArrayList<>();

    public Hero addHero(String name, String power, String originCity,
                        int combat, int intellect, int vigor, int charisma, int mobility,
                        int experience) {
        Hero hero = new Hero(name, power, originCity, combat, intellect, vigor, charisma, mobility, experience);
        heroes.add(hero);
        return hero;
    }

    public boolean removeHero(UUID id) {
        Hero hero = findById(id);
        if (hero == null) {
            return false;
        }
        heroes.remove(hero);
        return true;
    }

    public Hero findById(UUID id) {
        for (Hero hero : heroes) {
            if (hero.getId().equals(id)) {
                return hero;
            }
        }
        return null;
    }

    public List<Hero> getHeroes() {
        return Collections.unmodifiableList(heroes);
    }

    public List<String> getHeroesToString() {
        List<String> result = new ArrayList<>();
        for (Hero hero : heroes) {
            result.add(hero.toString());
        }
        return result;
    }
}