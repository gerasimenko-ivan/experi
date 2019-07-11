import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTest {


    @Test
    public void testPrint(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println(date.format(formatter));
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(date.format(formatter));
        formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        System.out.println(date.format(formatter));

        Date date2 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        System.out.println(dateFormat.format(date2));
    }
}
