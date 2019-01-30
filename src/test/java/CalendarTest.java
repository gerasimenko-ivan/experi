import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

    @Test
    public void test1() {
        LocalDate ld = LocalDate.now();
        Date d = new Date();

        //d.getDay()
        Calendar cl = Calendar.getInstance();
        //cl.get();
    }
}
