import org.testng.annotations.Test;

public class TypeConversion {

    @Test
    public void longFromString() {
        // Long.parseLong - java.lang.NumberFormatException: For input string: ""
        // Long.valueOf - java.lang.NumberFormatException: For input string: ""
        // new Long(a); - java.lang.NumberFormatException: For input string: ""

        String a = null;
        Long aL = parseLong(a);
        System.out.println(aL);
    }

    @Test
    public void doubleFromString() {

        String a = "234,234";
        Double aL = parseDouble(a);
        System.out.println(aL);
    }

    public Long parseLong(String longText) {
        try {
            return Long.parseLong(longText);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Double parseDouble(String doubleText) {
        try {
            return Double.parseDouble(doubleText.replace(",", "."));
        } catch (NumberFormatException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Error while parsing doubleText = '" + doubleText + "'");
            return null;
        }
    }
}
