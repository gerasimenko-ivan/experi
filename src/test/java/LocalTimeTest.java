import org.testng.annotations.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

public class LocalTimeTest {

    @Test
    public void test() {
        LocalTime localTime = LocalTime.now();

        LocalTime localTime1 = localTime.plusHours(9);
        LocalTime localTime2 = LocalTime.of(0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(localTime);
        System.out.println(formatter.format(localTime));
        System.out.println(localTime1);
        System.out.println(localTime2);
    }

}
