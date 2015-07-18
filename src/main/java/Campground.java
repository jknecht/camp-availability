import java.util.List;

/**
 * Created by jeff on 7/18/15.
 */
public class Campground {
    private String name;
    private List<CampAvailability> availabilityHistory;
    private boolean swimmingBeach;
    private boolean bikeTrails;
    private boolean hikingTrails;
    private boolean shower;
    private boolean flushToilets;
    private boolean waterFaucets;
    private boolean dumpStation;
    private boolean noMotorBoats;
    private boolean firewoodAvailable;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CampAvailability> getAvailabilityHistory() {
        return availabilityHistory;
    }

    public void setAvailabilityHistory(List<CampAvailability> availabilityHistory) {
        this.availabilityHistory = availabilityHistory;
    }

    public boolean isSwimmingBeach() {
        return swimmingBeach;
    }

    public void setSwimmingBeach(boolean swimmingBeach) {
        this.swimmingBeach = swimmingBeach;
    }

    public boolean isBikeTrails() {
        return bikeTrails;
    }

    public void setBikeTrails(boolean bikeTrails) {
        this.bikeTrails = bikeTrails;
    }

    public boolean isHikingTrails() {
        return hikingTrails;
    }

    public void setHikingTrails(boolean hikingTrails) {
        this.hikingTrails = hikingTrails;
    }

    public boolean isShower() {
        return shower;
    }

    public void setShower(boolean shower) {
        this.shower = shower;
    }

    public boolean isFlushToilets() {
        return flushToilets;
    }

    public void setFlushToilets(boolean flushToilets) {
        this.flushToilets = flushToilets;
    }

    public boolean isWaterFaucets() {
        return waterFaucets;
    }

    public void setWaterFaucets(boolean waterFaucets) {
        this.waterFaucets = waterFaucets;
    }

    public boolean isDumpStation() {
        return dumpStation;
    }

    public void setDumpStation(boolean dumpStation) {
        this.dumpStation = dumpStation;
    }

    public boolean isNoMotorBoats() {
        return noMotorBoats;
    }

    public void setNoMotorBoats(boolean noMotorBoats) {
        this.noMotorBoats = noMotorBoats;
    }

    public boolean isFirewoodAvailable() {
        return firewoodAvailable;
    }

    public void setFirewoodAvailable(boolean firewoodAvailable) {
        this.firewoodAvailable = firewoodAvailable;
    }

    @Override
    public String toString() {
        return "Campground{" +
                "name='" + name + '\'' +
                ", availabilityHistory=" + availabilityHistory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campground that = (Campground) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
