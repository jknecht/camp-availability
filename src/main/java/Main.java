
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jeff on 7/18/15.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        CampAvailabilityReader reader = new CampAvailabilityReader();
        List<CampAvailability> campAvailabilityList = reader.read(new File("data"));
        campAvailabilityList.forEach(campAvailability -> System.out.println(campAvailability));
    }
}
