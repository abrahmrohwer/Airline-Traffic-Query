import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Query6 {
    public static String Query6(Iterable<FlightRecord> input) {
        HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();

        for (FlightRecord r : input) {

            String before;
            String after;

            if (r.ORIGIN_STATE_ABR.compareTo(r.DEST_STATE_ABR) < 0) {
                before = r.ORIGIN_STATE_ABR;
                after = r.DEST_STATE_ABR;
            }
            else {
                before = r.DEST_STATE_ABR;
                after = r.ORIGIN_STATE_ABR;
            }

            if (map.containsKey(before)) {
                HashMap<String, Integer> temp = map.get(before);
                if (temp.containsKey(after)) {
                    temp.put(after, temp.get(after) + 1);
                    map.put(before, temp);
                } else {
                    temp.put(after, 1);
                    map.put(before, temp);
                }
            }
            else {
                HashMap<String, Integer> temp2 = new HashMap<String, Integer>();
                temp2.put(after, 1);
                map.put(before, temp2);
            }
        }

        String max_before = "";
        String max_after = "";
        Integer max_flights = 0;

        for (Map.Entry<String, HashMap<String, Integer>> entry_i : map.entrySet()) {
            String curr_before = entry_i.getKey();
            for (Map.Entry<String, Integer> entry_j : map.get(curr_before).entrySet()) {
                if (entry_j.getValue() > max_flights) {
                    max_before = curr_before;
                    max_after  = entry_j.getKey();
                    max_flights = entry_j.getValue();
                }
            }
        }

        return(max_before + "," + max_after);
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights1990.csv");
        String r = Query6(input);
        System.out.println(r);
    }
}
