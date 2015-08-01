
import com.google.gson.Gson;
import spark.Spark;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jeff on 7/18/15.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        CampAvailabilityReader reader = new CampAvailabilityReader();
        List<Campground> campgrounds = reader.read(new File("data"));
        //campgrounds.forEach(campAvailability -> System.out.println(campAvailability));

        Gson gson = new Gson();
        Spark.staticFileLocation("/www");
        Spark.get("/data", (req, res) -> campgrounds, gson::toJson);
    }
}
