import java.io.IOException;
import java.util.*;

public class Query10 {

    public static Iterable<String> Query10(Iterable<FlightRecord> input) {
        ArrayList<String> output = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (FlightRecord r : input) {
            if (r.ORIGIN.equals("CID")) {
                for (FlightRecord m : input) {
                    if (m.DEST.equals("LAX") && m.ORIGIN.equals(r.DEST)) {
                        set.add(r.ORIGIN + "->" + r.DEST + "->" + m.DEST);
                    }
                }
            }
        }
        return set;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2020.csv");
        Timer t = new Timer();
        t.start();
        Iterable<String> results = Query10(input);
        t.end();
        for (String s : results) {
            System.out.println(s);
        }
        System.out.println(t.elapsedSeconds());
    }
}
