import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeff on 7/18/15.
 */
public class CampAvailabilityReader {
    public List<CampAvailability> read(File dataDirectory) throws IOException {
        ArrayList<CampAvailability> campAvailabilityList = new ArrayList<>();

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
                    CampAvailability availability = new CampAvailability();
                    availability.setDate(date);
                    availability.setCampground(values.get(0).text());
                    try {
                        availability.setTrailerOrTentSitesAvailable(Integer.parseInt(values.get(1).text()));
                    } catch (NumberFormatException e) {
                        System.err.println("Exception parsing trailer or tent site availability");
                        System.err.println("date: " + date);
                        System.err.println("campground: " + availability.getCampground());
                        e.printStackTrace();
                        availability.setTrailerOrTentSitesAvailable(0);
                    }
                    try {
                        availability.setTentOnlySitesAvailable(Integer.parseInt(values.get(2).text()));
                    } catch (NumberFormatException e) {
                        System.err.println("Exception parsing tent only site availability");
                        System.err.println("date: " + date);
                        System.err.println("campground: " + availability.getCampground());
                        e.printStackTrace();
                        availability.setTentOnlySitesAvailable(0);
                    }
                    campAvailabilityList.add(availability);
                }
            }

        }
        return campAvailabilityList;
    }
}
