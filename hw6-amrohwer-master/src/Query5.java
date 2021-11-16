import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Query5 {
    public static String Query5(Iterable<FlightRecord> input) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (FlightRecord r : input) {
            if (map.containsKey(r.MONTH)) {
                map.put(r.MONTH, map.get(r.MONTH) + 1);

            } else {
                map.put(r.MONTH, 1);
            }
        }
        int maxFlights = 0;
        int maxMonth = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value > maxFlights){
                maxFlights = value;
                maxMonth = key;
            }
        }
        return(String.valueOf(maxMonth) + " had " + String.valueOf(maxFlights) + " flights");
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights1990.csv");
        String r = Query5(input);
        System.out.println(r);
    }
}
