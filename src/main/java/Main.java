
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

        Gson gson = new Gson();
        Spark.staticFileLocation("/www");
        Spark.get("/data", (req, res) -> reader.read(new File("data")), gson::toJson);
    }
}
