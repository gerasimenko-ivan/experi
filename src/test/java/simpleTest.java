import helpers.RegExpHelper;
import object.SpecialDateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class simpleTest {

    @Test
    public void test() {
        LocalDate now = LocalDate.now();
        LocalDate now2 = LocalDate.now();


        Double number = 9999.0;
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(df.format(number));
        System.out.println(df.format(4.55));
        System.out.println(df.format(4.56));
        System.out.println(df.format(4.44));
        System.out.println(df.format(4.66));

        String a = "Водитель ыва";
        String a2 = "Водитель";
        String a3 = "ыва Водитель ыва";
        String f = "Делопроизводитель";
        String f2 = "водительц";

        System.out.println(RegExpHelper.getSubstring("(\\s|^)водитель(\\s|$)", f.toLowerCase()));
        System.out.println(RegExpHelper.getSubstring("(\\s|^)водитель(\\s|$)", f2.toLowerCase()));
        System.out.println(RegExpHelper.getSubstring("(\\s|^)водитель(\\s|$)", a.toLowerCase()));
        System.out.println(RegExpHelper.getSubstring("(\\s|^)водитель(\\s|$)", a2.toLowerCase()));
        System.out.println(RegExpHelper.getSubstring("(\\s|^)водитель(\\s|$)", a3.toLowerCase()));
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
