import java.io.IOException;
import java.util.*;

public class Query7 {
    public static Iterable<String> Query7(Iterable<FlightRecord> input) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashSet<String> set = new HashSet<String>();

        for (FlightRecord r : input){
            if (r.ORIGIN_STATE_ABR.equals("IA")) {
                map.put(r.DEST_STATE_ABR, 1);
            }
        }
        for (FlightRecord r : input){
            if (!map.containsKey(r.DEST_STATE_ABR)){
                set.add(r.DEST_STATE_ABR);
            }
        }

        return set;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights1990.csv");
        Iterable<String> rs = Query7(input);
        for (String r : rs) {
            System.out.println(r);
        }
    }
}
