public class Ship {
    private String shipName;
    private String id;
    private String commander;
    private Module[] modules;

    public Ship(String shipName, String id, String commander){
        this.shipName = shipName;
        this.id = id;
        this.commander = commander;
        this.modules = new Module[5];
    }


    public String getShipName() {
        return shipName;
    }

    public String getID() {
        return id;
    }

    public String getCommander() {
        return commander;
    }

    public Module[] getModules() {
        return modules;
    }

    public void addModule(int index, Module mod) {
        if (index < 0 || index >= modules.length) {
            return;
        }

        if (modules[index] != null) {
            return;
        }

        modules[index] = mod;
    }

    public void removeModule(int index) {
        if (index < 0 || index >= modules.length) {
            throw new java.lang.IllegalArgumentException("invalid index number.");
        }

        modules[index] = null;
    }

    public Module getModule(int index) {
        if (index < 0 || index >= modules.length) {
            throw new java.lang.IllegalArgumentException("invalid index number.");
        }
        
        return modules[index];
    }

    public int getInstalledModulesCount() {
        int count = 0;
        for (Module module : modules) {
            if (module != null) {
                count++;
            }
        }
        return count;
    }

    public int getAvailableModulesCount() {
        return modules.length - getInstalledModulesCount();
    }

    public Module getModuleWithHighestEnergyUse() {
        Module maxModule = null;
        double maxEnergyUse = -1;

        for (Module module : modules) {
            if (module != null && module.getEnergyUse() > maxEnergyUse) {
                maxEnergyUse = module.getEnergyUse();
                maxModule = module;
            }
        }

        return maxModule;
    }
}