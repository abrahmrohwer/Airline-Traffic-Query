import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Query8 {
    public static Iterable<String> Query8(Iterable<FlightRecord> input) {
        HashMap<String, Integer> in = new HashMap<String, Integer>();
        HashMap<String, Integer> total = new HashMap<String, Integer>();

        for (FlightRecord r : input) {
            if (r.ORIGIN_STATE_ABR == r.DEST_STATE_ABR) {
                if (in.containsKey(r.ORIGIN_STATE_ABR)) {
                    in.put(r.ORIGIN_STATE_ABR, in.get(r.ORIGIN_STATE_ABR) + 1);
                }
                else {
                    in.put(r.ORIGIN_STATE_ABR, 1);
                }
            }
            else {
                if (in.containsKey(r.ORIGIN_STATE_ABR)) {
                    in.put(r.ORIGIN_STATE_ABR, in.get(r.ORIGIN_STATE_ABR));
                }
                else {
                    in.put(r.ORIGIN_STATE_ABR, 0);
                }
            }
            if (total.containsKey(r.ORIGIN_STATE_ABR)) {
                total.put(r.ORIGIN_STATE_ABR, total.get(r.ORIGIN_STATE_ABR) + 1);
            }
            else {
                total.put(r.ORIGIN_STATE_ABR, 1);
            }
        }

        HashSet<String> output = new HashSet<>();
        int i = 1;
        for (Map.Entry<String, Integer> entry : in.entrySet()){
            String state = entry.getKey();
            int numIn = entry.getValue();
            int numTotal = total.get(state);
            float y = (numIn/numTotal);
            DecimalFormat df = new DecimalFormat("#.000");
            String withThreeDigits = df.format(y);
            output.add(state + "=" + withThreeDigits);
            //System.out.println(i);
            //System.out.println(state + "=" + withThreeDigits);
            i = i + 1;
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2020.csv");
        Iterable<String> rs = Query8(input);
        for (String r : rs) {
            System.out.println(r);
        }
    }
}
