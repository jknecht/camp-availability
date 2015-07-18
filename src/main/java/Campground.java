import java.util.List;

/**
 * Created by jeff on 7/18/15.
 */
public class Campground {
    private String name;
    private List<CampAvailability> availabilityHistory;

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
