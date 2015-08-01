import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jeff on 7/18/15.
 */
public class CampAvailabilityReader {
    public List<Campground> read(File dataDirectory) throws IOException {
        ArrayList<Campground> campgrounds = new ArrayList<>();

        for (File file : dataDirectory.listFiles()) {
            String date = file.getName().substring(0, file.getName().lastIndexOf('.'));
            Document document = Jsoup.parse(file, "UTF-8");
            Element mainContent = document.body().getElementById("maincontent");
            Element table = mainContent.getElementsByTag("table").first();
            Element tableBody = table.getElementsByTag("tbody").first();
            Elements rows = tableBody.getElementsByTag("tr");
            for(Element row : rows) {
                Elements values = row.getElementsByTag("td");
                if (values.size() > 1) {
                    String name = values.get(0).text();
                    Campground campground = new Campground();
                    campground.setName(name);
                    int campgroundIndex = campgrounds.indexOf(campground);
                    if (campgroundIndex > -1) {
                        campground = campgrounds.get(campgroundIndex);
                    } else {
                        campground.setAvailabilityHistory(new ArrayList<>());
                        campground.setSwimmingBeach(values.get(4).getElementsByTag("img").size() > 0);
                        campground.setBikeTrails(values.get(5).getElementsByTag("img").size() > 0);
                        campground.setHikingTrails(values.get(6).getElementsByTag("img").size() > 0);
                        campground.setShower(values.get(7).getElementsByTag("img").size() > 0);
                        campground.setFlushToilets(values.get(8).getElementsByTag("img").size() > 0);
                        campground.setWaterFaucets(values.get(9).getElementsByTag("img").size() > 0);
                        campground.setDumpStation(values.get(10).getElementsByTag("img").size() > 0);
                        campground.setNoMotorBoats(values.get(11).getElementsByTag("img").size() > 0);
                        campground.setFirewoodAvailable(values.get(12).getElementsByTag("img").size() > 0);
                        campgrounds.add(campground);
                    }

                    CampAvailability availability = new CampAvailability();
                    availability.setDate(date);
                    try {
                        availability.setTrailerOrTentSitesAvailable(Integer.parseInt(values.get(1).text()));
                    } catch (NumberFormatException e) {
                        System.err.println("Exception parsing trailer or tent site availability");
                        System.err.println("date: " + date);
                        System.err.println("campground: " + name);
                        e.printStackTrace();
                        availability.setTrailerOrTentSitesAvailable(0);
                    }
                    try {
                        availability.setTentOnlySitesAvailable(Integer.parseInt(values.get(2).text()));
                    } catch (NumberFormatException e) {
                        System.err.println("Exception parsing tent only site availability");
                        System.err.println("date: " + date);
                        System.err.println("campground: " + name);
                        e.printStackTrace();
                        availability.setTentOnlySitesAvailable(0);
                    }

                    campground.getAvailabilityHistory().add(availability);
                    Collections.sort(campground.getAvailabilityHistory(), CampAvailability.dateComparator);
                }
            }

        }
        return campgrounds;
    }
}
