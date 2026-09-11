import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Ship ship;
    private static final ArrayList<Planet> planets = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to the Space Exploration Program ===");
        createNewShip();

        boolean running = true;
        while (running) {
            showMenu();
            int option = readInt("Select an option: ");

            switch (option) {
                case 1:
                    createNewShip();
                    break;
                case 2:
                    installModule();
                    break;
                case 3:
                    consultModules();
                    break;
                case 4:
                    consultSingleModule();
                    break;
                case 5:
                    modifyModule();
                    break;
                case 6:
                    removeModule();
                    break;
                case 7:
                    registerPlanet();
                    break;
                case 8:
                    consultPlanets();
                    break;
                case 9:
                    searchPlanet();
                    break;
                case 10:
                    modifyPlanet();
                    break;
                case 11:
                    removePlanet();
                    break;
                case 12:
                    showMissionReport();
                    break;
                case 13:
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create a new ship");
        System.out.println("2. Install a module");
        System.out.println("3. Consult all modules");
        System.out.println("4. Consult a single module");
        System.out.println("5. Modify a module");
        System.out.println("6. Remove a module");
        System.out.println("7. Register a planet");
        System.out.println("8. Consult all planets");
        System.out.println("9. Search for a planet by code");
        System.out.println("10. Modify a planet");
        System.out.println("11. Remove a planet");
        System.out.println("12. Show mission report");
        System.out.println("13. Exit");
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer.");
            } finally {
                scanner.nextLine();
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid decimal number.");
            } finally {
                scanner.nextLine();
            }
        }
    }

    private static void createNewShip() {
        System.out.println("\n--- SHIP REGISTRATION ---");
        System.out.print("Enter ship name: ");
        String name = scanner.nextLine();
        System.out.print("Enter identification code: ");
        String id = scanner.nextLine();
        System.out.print("Enter commander name: ");
        String commander = scanner.nextLine();

        ship = new Ship(name, id, commander);
        planets.clear();
        System.out.println("Ship created successfully. The exploration has been reset.");
    }

    private static void installModule() {
        System.out.println("\n--- INSTALL MODULE ---");
        int index = readInt("Enter the module position (0-4): ");

        try {
            System.out.print("Module code: ");
            String code = scanner.nextLine();
            System.out.print("Module name: ");
            String name = scanner.nextLine();
            System.out.print("Module type: ");
            String type = scanner.nextLine();
            double energy = readDouble("Energy consumption (> 0): ");

            System.out.print("Is it active? (true/false): ");
            boolean state = scanner.nextBoolean();
            scanner.nextLine();

            Module newModule = new Module(code, name, type, energy, state);
            ship.addModule(index, newModule);
            System.out.println("Module installed successfully in position " + index + ".");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error installing module: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid state input. Enter 'true' or 'false'.");
            scanner.nextLine();
        }
    }

    private static void consultModules() {
        System.out.println("\n--- INSTALLED MODULES ---");
        Module[] mods = ship.getModules();
        boolean empty = true;

        for (int i = 0; i < mods.length; i++) {
            if (mods[i] != null) {
                System.out.println("Position [" + i + "]: " + mods[i]);
                empty = false;
            }
        }

        if (empty) {
            System.out.println("There are no modules installed on the ship.");
        }
    }

    private static void consultSingleModule() {
        int index = readInt("Enter the position to consult (0-4): ");
        try {
            Module mod = ship.getModule(index);
            if (mod != null) {
                System.out.println("Position [" + index + "]: " + mod);
            } else {
                System.out.println("Position " + index + " is EMPTY.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void modifyModule() {
        int index = readInt("Enter the position of the module to modify (0-4): ");
        try {
            Module mod = ship.getModule(index);
            if (mod == null) {
                System.out.println("It cannot be modified. The position is EMPTY.");
                return;
            }

            double newEnergy = readDouble("Enter new energy consumption (> 0): ");
            System.out.print("Active state? (true/false): ");
            boolean newState = scanner.nextBoolean();
            scanner.nextLine();

            mod.setEnergyUse(newEnergy);
            mod.setState(newState);
            System.out.println("Module updated successfully.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeModule() {
        int index = readInt("Enter the position of the module to remove (0-4): ");
        try {
            ship.removeModule(index);
            System.out.println("Module removed successfully from position " + index + ".");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registerPlanet() {
        System.out.println("\n--- REGISTER PLANET ---");
        System.out.print("Planet code: ");
        String code = scanner.nextLine();

        if (findPlanetByCode(code) != null) {
            System.out.println("Error: A planet with code '" + code + "' is already registered.");
            return;
        }

        System.out.print("Planet name: ");
        String name = scanner.nextLine();
        double distance = readDouble("Distance from the ship (> 0): ");
        double temp = readDouble("Temperature (°C): ");
        int habitability = readInt("Habitability level (0-100): ");
        Planet newPlanet = new Planet(code, name, distance, temp, habitability);
        planets.add(newPlanet);
        System.out.println("Planet registered successfully.");
    }

    private static void consultPlanets() {
        System.out.println("\n--- DISCOVERED PLANETS ---");
        if (planets.isEmpty()) {
            System.out.println("No planet has been discovered yet.");
            return;
        }

        for (Planet p : planets) {
            System.out.println(p);
        }
    }

    private static void searchPlanet() {
        System.out.print("Enter the planet code to search: ");
        String code = scanner.nextLine();
        Planet p = findPlanetByCode(code);

        if (p != null) {
            System.out.println("Planet found: " + p);
        } else {
            System.out.println("No planet was found with code '" + code + "'.");
        }
    }

    private static void modifyPlanet() {
        System.out.print("Enter the code of the planet to modify: ");
        String code = scanner.nextLine();
        Planet p = findPlanetByCode(code);

        if (p == null) {
            System.out.println("Error: The planet with code '" + code + "' does not exist.");
            return;
        }

        double distance = readDouble("New distance (> 0): ");
        double temp = readDouble("New temperature (°C): ");
        int habitability = readInt("New habitability level (0-100): ");
        p.setDistanceShip(distance);
        p.setTemperature((float) temp);
        p.setHabitability(habitability);
        System.out.println("Planet information updated successfully.");
    }

    private static void removePlanet() {
        System.out.print("Enter the code of the planet to remove: ");
        String code = scanner.nextLine();
        Planet p = findPlanetByCode(code);

        if (p != null) {
            planets.remove(p);
            System.out.println("Planet removed successfully from the registry.");
        } else {
            System.out.println("Error: No planet was found with code '" + code + "'.");
        }
    }

    private static Planet findPlanetByCode(String code) {
        for (Planet p : planets) {
            if (p.getCode().equalsIgnoreCase(code)) {
                return p;
            }
        }
        return null;
    }

    private static void showMissionReport() {
        System.out.println("\n========= SPACE MISSION REPORT =========");
        System.out.println("Ship: " + ship.getShipName() + " (ID: " + ship.getID() + ") | Commander: " + ship.getCommander());
        System.out.println("Installed modules: " + ship.getInstalledModulesCount());
        System.out.println("Available module spaces: " + ship.getAvailableSpacesCount());

        Module maxEnergyMod = ship.getModuleWithHighestEnergyUse();
        if (maxEnergyMod != null) {
            System.out.println("Highest power consumption module: " + maxEnergyMod.getName() + " (" + maxEnergyMod.getEnergyUse() + " kW)");
        } else {
            System.out.println("Highest power consumption module: N/A (No modules)");
        }

        System.out.println("\nDiscovered planets: " + planets.size());
        if (!planets.isEmpty()) {
            Planet mostHabitable = planets.get(0);
            Planet leastHabitable = planets.get(0);
            double totalHabitability = 0;

            for (Planet p : planets) {
                if (p.getHabitability() > mostHabitable.getHabitability()) {
                    mostHabitable = p;
                }
                if (p.getHabitability() < leastHabitable.getHabitability()) {
                    leastHabitable = p;
                }
                totalHabitability += p.getHabitability();
            }

            double average = totalHabitability / planets.size();

            System.out.println("Most habitable planet: " + mostHabitable.getName() + " (" + mostHabitable.getHabitability() + "/100)");
            System.out.println("Least habitable planet: " + leastHabitable.getName() + " (" + leastHabitable.getHabitability() + "/100)");
            System.out.printf("Average habitability: %.2f / 100\n", average);
        } else {
            System.out.println("Planet metrics: No calculations were made because the list is empty.");
        }
        System.out.println("========================================");
    }
}