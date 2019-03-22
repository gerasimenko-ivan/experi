package boxingUnboxing;

import org.testng.annotations.Test;

public class BoxingUnboxing {

    @Test
    public void test1() {
        A a = new A();
        a.name = "Hello, World!";

        Object o = a;
        System.out.println(((A) o).name);
    }
}
