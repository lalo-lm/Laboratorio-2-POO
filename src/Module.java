public class Module {
    private String name;
    private String code;
    private String type;
    private double energyUse;
    private boolean state;

    public Module(String name, String code, String type, double energyUse, boolean state) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.energyUse = energyUse;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public double getEnergyUse() {
        return energyUse;
    }

    public boolean getState() {
        return state;
    }

    public void setEnergyUse(double energyUse) {
        if (energyUse <= 0) {
            throw new java.lang.IllegalArgumentException("Energy use must be greater than zero.");
        }
        this.energyUse = energyUse;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String toString() {
        return "Module Name: " + name + "| Code: " + code + "| Type: " + type + "| Energy Use: " + energyUse + "| State: " + (state ? "Active" : "Inactive");
    }
}