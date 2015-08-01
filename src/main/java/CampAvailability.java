import java.util.Comparator;

/**
 * Created by jeff on 7/18/15.
 */
public class CampAvailability {
    private String date;
    private int trailerOrTentSitesAvailable;
    private int tentOnlySitesAvailable;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTrailerOrTentSitesAvailable() {
        return trailerOrTentSitesAvailable;
    }

    public void setTrailerOrTentSitesAvailable(int trailerOrTentSitesAvailable) {
        this.trailerOrTentSitesAvailable = trailerOrTentSitesAvailable;
    }

    public int getTentOnlySitesAvailable() {
        return tentOnlySitesAvailable;
    }

    public void setTentOnlySitesAvailable(int tentOnlySitesAvailable) {
        this.tentOnlySitesAvailable = tentOnlySitesAvailable;
    }

    @Override
    public String toString() {
        return "CampAvailability{" +
                "date='" + date + '\'' +
                ", trailerOrTentSitesAvailable=" + trailerOrTentSitesAvailable +
                ", tentOnlySitesAvailable=" + tentOnlySitesAvailable +
                '}';
    }

    public static final Comparator<CampAvailability> dateComparator = new Comparator<CampAvailability>() {
        @Override
        public int compare(CampAvailability o1, CampAvailability o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };
}
