package func.as.param;

public class Test {

    @org.testng.annotations.Test
    public void test() {

        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};

        System.out.println("nightmare starts ------------");
        for (int i = 0; i < arr.length - 1; i++) {

            // func to parameterize
            int param1 = 3;
            int param2 = 10;
            String x = 2 * param1 + "-" + param2;
            System.out.println(x+arr[i]);
        }
        System.out.println("nightmare is over ------------");

        System.out.println("nightmare starts ------------");
        for (int i = 0; i < arr.length - 1; i++) {

            // func to parameterize
            int param1 = 3;
            int param2 = 10;
            String x = 5.2 * param1 + ":" + 4.3 * param2;
            System.out.println(x+arr[i]);
        }
        System.out.println("nightmare is over ------------");


        TwoParamInterface func1 = (p1, p2) -> 2 * p1 + "-" + p2;
        TwoParamInterface func2 = (p1, p2) -> 5.2 * p1 + ":" + 4.3 * p2;

        funcAsParam(arr, func1);
        funcAsParam(arr, func2);

    }

    private void funcAsParam(String[] arr, TwoParamInterface twoParamInterface) {
        System.out.println("nightmare starts ------------");
        for (int i = 0; i < arr.length - 1; i++) {

            // func to parameterize
            int param1 = 3;
            int param2 = 10;
            String x = twoParamInterface.get(param1, param2);
            System.out.println(x+arr[i]);
        }
        System.out.println("nightmare is over ------------");
    }

}
