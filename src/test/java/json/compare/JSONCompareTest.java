package json.compare;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONCompareTest {

    @Test
    public void test() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<HashMap<String, Object>>() {};

        Map<String, Object> rightMap = null;
        Map<String, Object> leftMap = null;
        try {
            leftMap = mapper.readValue("[{\"types\": [\"a\", 4]}]", type);
            rightMap = mapper.readValue("{}", type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
        Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

        MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

        System.out.println("Entries only on the left\n--------------------------");
        difference.entriesOnlyOnLeft().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries only on the right\n--------------------------");
        difference.entriesOnlyOnRight().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries differing\n--------------------------");
        difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
