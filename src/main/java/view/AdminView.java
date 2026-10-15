package view;

import controller.AdminController;

import java.util.List;
import java.util.Scanner;
import static java.lang.IO.println;
import static java.lang.IO.readln;

public class AdminView {

    private final AdminController controller;
    private final Scanner scanner = new Scanner(System.in);

    public AdminView(AdminController controller) {
        this.controller = controller;
    }

    public void run() {
        int option;
        do {
            printMenu();
            option = readInt("Seleccione una opción: ");
            switch (option) {
                case 1 -> addHero();
                case 2 -> removeHero();
                case 3 -> addMission();
                case 4 -> removeMission();
                case 5 -> addFan();
                case 6 -> removeFan();
                case 7 -> addTeam();
                case 8 -> removeTeam();
                case 9 -> addHeroATeam();
                case 10 -> removeHeroDeTeam();
                case 11 -> addTeamAMission();
                case 12 -> removeTeamDeMission();
                case 13 -> showList(controller.getFansToString());
                case 14 -> showList(controller.getMissionesToString());
                case 15 -> showList(controller.getHeroesToString());
                case 16 -> showList(controller.getTeamsToString());
                case 0 -> println("Volviendo al menú principal.");
                default -> println("Opción inválida.");
            }
        } while (option != 0);
    }

    private void printMenu() {
        println("");
        println("=== Módulo administrador ===");
        println("1. Registrar héroe en la base de datos.");
        println("2. Retirar héroe de la base de datos.");
        println("3. Registrar misión.");
        println("4. Retirar misión de la base de datos.");
        println("5. Registrar fanático en la base de datos.");
        println("6. Eliminar fanático de la base de datos.");
        println("7. Registrar equipo en la base de datos.");
        println("8. Disolver equipo de la base de datos.");
        println("9. Agregar héroe a un equipo.");
        println("10. Retirar héroe de un equipo.");
        println("11. Asignar equipo a una misión.");
        println("12. Retirar equipo de una misión.");
        println("13. Ver la lista de fanáticos.");
        println("14. Ver la lista de misiones.");
        println("15. Ver la lista de héroes.");
        println("16. Ver la lista de equipos.");
        println("0. Volver al menú principal.");
    }

    private void addHero() {
        String name = readln("Nombre: ");
        String power = readln("Poder: ");
        String originCity = readln("Ciudad de origen: ");
        int combat = readInt("Combate: ");
        int intellect = readInt("Intelecto: ");
        int vigor = readInt("Vigor: ");
        int charisma = readInt("Carisma: ");
        int mobility = readInt("Movilidad: ");
        int experience = readInt("Experiencia: ");
        controller.addHero(name, power, originCity, combat, intellect, vigor, charisma, mobility, experience);
        println("Héroe registrado.");
    }

    private void removeHero() {
        String id = chooseId("Héroes", controller.getHeroesToString());
        if (id != null) {
            report(controller.removeHero(id), "Héroe eliminado.", "El héroe no existe.");
        }
    }

    private void addMission() {
        String codeName = readln("Nombre clave de la mission: ");
        int threatLevel = readInt("nivel de amenaza de la mission de 1 a 10: ");
        int durationInHours = readInt("Duracion de la mission en horas: ");
        String city = readln("Nombre de la ciudad de la mission: ");
        controller.addMission(codeName, threatLevel, durationInHours, city);
        println("Mission registrado.");
    }

    private void removeMission() {
        String id = chooseId("Missiones", controller.getMissionesToString());
        if (id != null) {
            report(controller.removeMission(id), "Mission eliminado.", "La mission no existe.");
        }
    }

    private void addFan() {
        String username = readln("Nombre de usuario: ");
        String password = readln("Contraseña: ");
        String name = readln("Nombre: ");
        String lastName = readln("Apellido: ");
        int age = readInt("Edad: ");
        controller.addFan(username, password, name, lastName, age);
        println("Fanático registrado.");
    }

    private void removeFan() {
        String id = chooseId("Fanáticos", controller.getFansToString());
        if (id != null) {
            report(controller.removeFan(id), "Fanático eliminado.", "El fanático no existe.");
        }
    }

    private void addTeam() {
        String name = readln("Nombre del equipo: ");
        controller.addTeam(name);
        println("Equipo registrado.");
    }

    private void removeTeam() {
        String id = chooseId("Equipos", controller.getTeamsToString());
        if (id != null) {
            report(controller.removeTeam(id), "Equipo eliminado.", "El equipo no existe.");
        }
    }

    private void addHeroATeam() {
        String heroId = chooseId("Héroes", controller.getHeroesToString());
        if (heroId == null) {
            return;
        }
        String teamId = chooseId("Equipos", controller.getTeamsToString());
        if (teamId == null) {
            return;
        }
        report(controller.addHeroATeam(heroId, teamId), "Héroe agregado al equipo.", "No se pudo agregar el héroe al equipo.");
    }

    private void removeHeroDeTeam() {
        String heroId = chooseId("Héroes", controller.getHeroesToString());
        if (heroId == null) {
            return;
        }
        String teamId = chooseId("Equipos", controller.getTeamsToString());
        if (teamId == null) {
            return;
        }
        report(controller.removeHeroDeTeam(heroId, teamId), "Héroe retirado del equipo.", "No se pudo retirar el héroe del equipo.");
    }

    private void addTeamAMission() {
        String teamId = chooseId("Equipos", controller.getTeamsToString());
        if (teamId == null) {
            return;
        }
        String missionId = chooseId("Misiones", controller.getMissionesToString());
        if (missionId == null) {
            return;
        }
        report(controller.addTeamAMission(teamId, missionId), "Equipo asignado a la misión.", "No se pudo asignar el equipo a la misión.");
    }

    private void removeTeamDeMission() {
        String missionId = chooseId("Misiones", controller.getMissionesToString());
        if (missionId == null) {
            return;
        }
        report(controller.removeTeamDeMission(missionId), "Equipo retirado de la misión.", "No se pudo retirar el equipo de la misión.");
    }
    private String chooseId(String title, List<String> items) {
        println(title + ":");
        if (!showList(items)) {
            return null;
        }
        return readln("Ingrese el id: ").trim();
    }

    private boolean showList(List<String> items) {
        if (items.isEmpty()) {
            println("No hay elementos.");
            return false;
        }
        for (String item : items) {
            println(item);
        }
        return true;
    }

    private void report(boolean success, String successMessage, String failureMessage) {
        println(success ? successMessage : failureMessage);
    }

    private void println(String msg) {
        System.out.println(msg);
    }

    private String readln(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        return Integer.parseInt(readln(prompt).trim());
    }
}