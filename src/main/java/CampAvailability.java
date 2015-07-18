/**
 * Created by jeff on 7/18/15.
 */
public class CampAvailability {
    private String date;
    private String campground;
    private int trailerOrTentSitesAvailable;
    private int tentOnlySitesAvailable;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCampground() {
        return campground;
    }

    public void setCampground(String campground) {
        this.campground = campground;
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
                ", campground='" + campground + '\'' +
                ", trailerOrTentSitesAvailable=" + trailerOrTentSitesAvailable +
                ", tentOnlySitesAvailable=" + tentOnlySitesAvailable +
                '}';
    }
}
