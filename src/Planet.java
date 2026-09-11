public class Planet {
    private String name;
    private String code;
    private double distanceShip;
    private double temperature;
    private int habitability;

    public Planet(String name, String code, double distanceShip, double temperature, int habitability) {
        this.name = name;
        this.code = code;
        this.distanceShip = distanceShip;
        this.temperature = temperature;
        this.habitability = habitability;
    }

    public void validateData(double distanceShip, int habitability) {
        if (distanceShip <= 0) {
            throw new java.lang.IllegalArgumentException("Distance must be greater than zero.");
        }
        if (habitability < 0 || habitability > 100) {
            throw new java.lang.IllegalArgumentException("Habitability must be between 0 and 100.");
        }
    }

    public String getName() {
        return name;
    }
    
    public String getCode() {
        return code;
    }

    public double getDistanceShip() {
        return distanceShip;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHabitability() {
        return habitability;
    }

    public void setDistanceShip(double distanceShip) {
        validateData(distanceShip, this.habitability);
        this.distanceShip = distanceShip;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setHabitability(int habitability) {
        validateData(this.distanceShip, habitability);
        this.habitability = habitability;
    }

    public String toString() {
        return "Planet Name: " + name + "| Code: " + code + "| Distance from Ship: " + distanceShip + "AL | Temperature: " + temperature + "°C | Habitability: " + habitability + "%";
    }
}