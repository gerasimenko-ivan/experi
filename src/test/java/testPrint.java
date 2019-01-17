import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class testPrint {

    @Test
    public void test() throws IOException {
        String in = "I";
        for (int i = 0; i < 70; i++) {
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder();
            sb.append(in);
            for (char c : in.toCharArray()) {
                char a = new Character((char)rnd.nextInt(256));
                sb.insert(rnd.nextInt(sb.length()), a);
                /*if (rnd.nextBoolean())
                    sb.insert(rnd.nextInt(sb.length()), a);
                else
                    sb.append(a);*/
            }
            in = sb.toString();
            in = in.substring(0, in.length() > 70 ? 70 : in.length());
            System.out.println(in);
        }

        char[][] arr = new char[70][70];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = '\14'; //new Character((char)(69+i)); //'\33' =  / '\14' = 
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(i);
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
