import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class simpleTest {

    @Test
    public void test() {
        SimpleObject a = new SimpleObject().withID(0);
        SimpleObject b = new SimpleObject().withID(666);
        Assert.assertEquals(a, b);
    }
}
