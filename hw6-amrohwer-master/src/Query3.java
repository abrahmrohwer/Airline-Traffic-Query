import java.io.IOException;
import java.util.*;

public class Query3 {

    public static int Query3(Iterable<FlightRecord> input) {
        HashSet<String> set = new HashSet<String>();

        for (FlightRecord r : input){
            if (r.ORIGIN.equals("CID")) {
                if (!set.contains(r.DEST)) {
                    set.add(r.DEST);

                }
            }
        }

        return set.size();

    }

    public static void main(String[] args) throws IOException {
        Iterable<FlightRecord> input = DataImporter.getData("flights2019.csv");
        int results = Query3(input);
        System.out.println(results);
    }
}
