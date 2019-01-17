import helpers.RegExpHelper;
import org.testng.annotations.Test;

public class regexpTest {


    @Test
    public void test1() {
        String substring = RegExpHelper.getSubstring("(^[А-Я]{2}\\d{4}\\d{2,3}/)|(^\\d{4}[А-Я]{2}\\d{2,3}/)", "0302НВ77 / - / Фронтальный погрузчик/ АМКОДОР 332C4 (-/-/60)/ отсутствует");
        substring = RegExpHelper.getSubstring("(^[А-Я]{2}\\d{4}\\d{2,3}\\s)|(^\\d{4}[А-Я]{2}\\d{2,3}\\s)", "НВ030277 / - / Фронтальный погрузчик/ АМКОДОР 332C4 (-/-/60)/ отсутствует");
        substring = "";
    }
}
