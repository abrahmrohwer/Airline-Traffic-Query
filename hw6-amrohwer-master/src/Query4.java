import java.io.IOException;
import java.util.*;

public class Query4 {

    public static Iterable<String> Query4(Iterable<FlightRecord> input) {
        HashMap<String, Integer> set = new HashMap<String, Integer>();
        //int flyCount = 1;

        for (FlightRecord r : input) {
            if (r.ORIGIN.equals("CID")) {
                if (set.containsKey(r.DEST)){
                    set.put(r.DEST, set.get(r.DEST) + 1);
                }
                else {
                    set.put(r.DEST, 1);
                }
            }
        }
        ArrayList<String> output = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : set.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            output.add(key+"="+value);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2005.csv");
        Iterable<String> results = Query4(input);
        for (String s : results) {
            System.out.println(s);
        }
    }
}
