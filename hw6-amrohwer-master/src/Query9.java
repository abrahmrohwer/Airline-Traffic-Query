import java.io.IOException;
import java.util.*;

public class Query9 {
    public static Iterable<String> Query9(Iterable<FlightRecord> input) {
        TreeMap<String, TreeMap<String, Integer>> map = new TreeMap<String, TreeMap<String, Integer>>();

        for (FlightRecord r : input) {

            if (map.containsKey(r.DEST_STATE_ABR)) {
                TreeMap<String, Integer> temp = map.get(r.DEST_STATE_ABR);
                if (temp.containsKey(r.UNIQUE_CARRIER_NAME)) {
                    temp.put(r.UNIQUE_CARRIER_NAME, temp.get(r.UNIQUE_CARRIER_NAME) + 1);
                    map.put(r.DEST_STATE_ABR, temp);
                } else {
                    temp.put(r.UNIQUE_CARRIER_NAME, 1);
                    map.put(r.DEST_STATE_ABR, temp);
                }
            }
            else {
                TreeMap<String, Integer> temp2 = new TreeMap<String, Integer>();
                temp2.put(r.UNIQUE_CARRIER_NAME, 1);
                map.put(r.DEST_STATE_ABR, temp2);
            }
        }
        ArrayList<String> output = new ArrayList<String>();

        for (Map.Entry<String, TreeMap<String, Integer>> entry_i : map.entrySet()) {
            String curr_before = entry_i.getKey();

            int stateMaxFlights = 0;
            String stateMaxAirline = "";

            for (Map.Entry<String, Integer> entry_j : map.get(curr_before).entrySet()) {
                if (entry_j.getValue() > stateMaxFlights) {
                    stateMaxAirline = entry_j.getKey();
                    stateMaxFlights = entry_j.getValue();
                }
            }
            output.add(curr_before + "," + stateMaxAirline);
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2005.csv");
        Iterable<String> rs = Query9(input);
        for (String r : rs) {
            System.out.println(r);
        }
    }
}
