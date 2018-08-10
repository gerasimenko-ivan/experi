package hashTable;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class HashTableTests {

    @Test
    public void groupingDishesTest() {
        String[][] dishes = {{"Salad", "Tomato", "Cucumber", "Salad", "Sauce"},
                            {"Pizza", "Tomato", "Sausage", "Sauce", "Dough"},
                            {"Quesadilla", "Chicken", "Cheese", "Sauce"},
                            {"Sandwich", "Salad", "Bread", "Tomato", "Cheese"}};
        String[][] groupedExp = {{"Cheese", "Quesadilla", "Sandwich"},
                            {"Salad", "Salad", "Sandwich"},
                            {"Sauce", "Pizza", "Quesadilla", "Salad"},
                            {"Tomato", "Pizza", "Salad", "Sandwich"}};
        String[][] grouped = groupingDishes(dishes);
        Assert.assertEquals(grouped, groupedExp);
    }

    String[][] groupingDishes(String[][] dishes) {
        /*String A is lexicographically smaller than string B either if A is a prefix of B (and A ≠ B), or if there exists
        such index i (0 ≤ i < min(A.length, B.length)), that Ai < Bi, and for any j (0 ≤ j < i) Aj = Bj.
        The lexicographic comparison of strings is implemented by operator < in modern programming languages.*/
        HashMap<String, HashMap<String, String>> ingridientsMap = new HashMap<>();
        for (int i = 0; i < dishes.length; i++) {
            String dish = dishes[i][0];
            for (int j = 1; j < dishes[i].length; j++) {
                String ingridient = dishes[i][j];
                if (ingridientsMap.containsKey(ingridient)) {
                    if (!ingridientsMap.get(ingridient).containsKey(dish)) {
                        ingridientsMap.get(ingridient).put(dish, dish);
                    }
                } else {
                    ingridientsMap.put(ingridient, new HashMap<String, String>());
                    ingridientsMap.get(ingridient).put(dish, dish);
                }
            }
        }
        List<String> ingridients =
                ingridientsMap.keySet().stream()
                        .filter(k -> ingridientsMap.get(k).size() > 1)
                        .sorted().collect(Collectors.toList());
        String[][] result = new String[ingridients.size()][];
        for (int i = 0; i < result.length; i++) {
            List<String> dishesList =
                    ingridientsMap.get(ingridients.get(i)).keySet().stream().sorted().collect(Collectors.toList());
            dishesList.add(0, ingridients.get(i));
            result[i] = dishesList.toArray(new String[dishesList.size()]);
        }
        return result;
    }


    @Test
    public void areFollowingPatternsTest() {
        String[] strings = {"cat", "dog", "dog"};
        String[] patterns = {"a", "b", "b"};
        boolean b = areFollowingPatterns(strings, patterns);

        String[] strings2 = {"cat", "dog", "doggy"};
        String[] patterns2 = {"a", "b", "b"};
        boolean c = areFollowingPatterns(strings2, patterns2);
    }

    boolean areFollowingPatterns(String[] strings, String[] patterns) {
        HashMap<String, String> stringToPattern = new HashMap<>();
        HashMap<String, String> patternToString = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            String stringCurrent = strings[i];
            String patternCurrent = patterns[i];
            if (stringToPattern.containsKey(stringCurrent)) {
                if (!stringToPattern.get(stringCurrent).equals(patternCurrent)) {
                    return false;
                }
            } else {
                stringToPattern.put(stringCurrent, patternCurrent);
            }
            if (patternToString.containsKey(patternCurrent)) {
                if (!patternToString.get(patternCurrent).equals(stringCurrent)) {
                    return false;
                }
            } else {
                patternToString.put(patternCurrent, stringCurrent);
            }
        }
        return true;
    }



    @Test
    public void containCloseNumbersTest() {
        int[] nums = {0, 1, 2, 3, 5, 2};
        int k = 3;
        boolean b = containsCloseNums(nums, k);

        k = 2;
        b = containsCloseNums(nums, k);
    }

    boolean containsCloseNums(int[] nums, int k) {
        HashMap<Integer, Integer> numsIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int numCurrent = nums[i];
            if (numsIndex.containsKey(numCurrent) && (i - numsIndex.get(numCurrent) <= k)) {
                return true;
            }
            numsIndex.put(numCurrent, i);
        }
        return false;
    }


    @Test
    public void possibleSumTest() {
        int[] coins = {10, 50, 100};
        int[] quantity = {1, 2, 1};

        Integer[] integers = possibleSumsValues(coins, quantity);
        int i = possibleSums(coins, quantity);
        int nine = 9;

        int[] coins2 = {10, 55, 130};
        int i2 = possibleSums(coins2, quantity);
        Integer[] integers2 = possibleSumsValues(coins2, quantity);

        int[] coins3 = {10, 51, 113};
        Integer[] integers3 = possibleSumsValues(coins3, quantity);
        int i3 = possibleSums(coins3, quantity);
        Assert.assertEquals(nine, 9);
    }

    Integer[] possibleSumsValues(int[] coins, int[] quantity) {
        HashSet<Integer> sums = new HashSet<>();
        HashSet<Integer> newSums;
        sums.add(0);
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int coinQuantity = quantity[i];
            newSums = new HashSet<>();

            Iterator<Integer> iterator = sums.iterator();
            while (iterator.hasNext()) {
                Integer nextSum = iterator.next();
                for (int j = 1; j <= coinQuantity; j++) {
                    int newSum = nextSum + coin * j;
                    if (!newSums.contains(newSum)) {
                        newSums.add(newSum);
                    }
                }
            }
            sums.addAll(newSums);
            /*for (int j = 0; j < coinQuantity; j++) {
                Iterator<Integer> iterator = sums.iterator();
                while (iterator.hasNext()) {
                    Integer nextSum = iterator.next();
                    if (!sums.contains(nextSum+coin)) {
                        newSums.add(nextSum+coin);
                    }
                }
            }
            sums.addAll(newSums);*/
            /*for (int j = 0; j < coinQuantity; j++) {
                //if (sums.contains())
                Iterator<Integer> iterator = sums.iterator();
                while (iterator.hasNext()) {
                    Integer nextSum = iterator.next();
                    if (!sums.contains(nextSum+coin)) {
                        newSums.add(nextSum+coin);
                    }
                }
                sums.addAll(newSums);
            }*/
        }
        sums.remove(0);
        return sums.toArray(new Integer[sums.size()]);
    }

    int possibleSums(int[] coins, int[] quantity) {
        HashSet<Integer> sums = new HashSet<>();
        HashSet<Integer> newSums;
        sums.add(0);
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int coinQuantity = quantity[i];
            newSums = new HashSet<>();

            Iterator<Integer> iterator = sums.iterator();
            while (iterator.hasNext()) {
                Integer nextSum = iterator.next();
                for (int j = 1; j <= coinQuantity; j++) {
                    int newSum = nextSum + coin * j;
                    if (!newSums.contains(newSum)) {
                        newSums.add(newSum);
                    }
                }
            }
            sums.addAll(newSums);
        }
        return sums.size() - 1;
    }




    @Test
    public void swapLexOrderTest(){
        String str = "abdc";
        int[][] pairs = {{1, 4}, {3, 4}};
        String extSwap = "dbca";
        String swap = swapLexOrder(str, pairs);
        ;

        str = "acxrabdz";
        int[][] pairs2 = {{1,3}, {6,8}, {3,8}, {2,7}};
        extSwap = "zdxrabca";
        swap = swapLexOrder(str, pairs2);
        ;
    }

    /*String swapLexOrder(String str, int[][] pairs) {
        if (str.length() == 1) return str;
        if (pairs.length == 0) return str;
        String temp;
        HashSet<String> swaps = new HashSet<>();
        for (int i = 0; i < Math.pow(pairs.length, 2); i++) {
            for (int rev = 0; rev < 2; rev++) {
                temp = str;
                for (int j = 0; j < pairs.length; j++) {
                    int index = (rev == 0 ? j : (pairs.length - j - 1));
                    if ((index + 1) == ((index + 1) & (i + 1))) {
                        char[] chars = temp.toCharArray();
                        char a = chars[pairs[index][0] - 1];
                        chars[pairs[index][0] - 1] = chars[pairs[index][1] - 1];
                        chars[pairs[index][1] - 1] = a;
                        temp = String.valueOf(chars);
                    }
                }
                swaps.add(temp);
            }
        }
        List<String> list = swaps.parallelStream().collect(Collectors.toList());
        Collections.sort(list);
        return list.get(list.size());
    }*/

    String swapLexOrder(String str, int[][] pairs) {
        if (str.length() == 1) return str;
        if (pairs.length == 0) return str;

        List<HashSet<Integer>> listOfPairsSets = new ArrayList<>();
        listOfPairsSets.add(new HashSet<>());
        listOfPairsSets.get(0).add(pairs[0][0]);
        listOfPairsSets.get(0).add(pairs[0][1]);

        for (int i = 1; i < pairs.length; i++) {
            boolean isAdded = false;
            int indexAdded = -1;
            for (int j = 0; j < listOfPairsSets.size(); j++) {
                if (listOfPairsSets.get(j).contains(pairs[i][0])) {
                    if (isAdded) {
                        listOfPairsSets.get(indexAdded).addAll(listOfPairsSets.get(j));
                        listOfPairsSets.remove(j);
                        break;
                    }
                    listOfPairsSets.get(j).add(pairs[i][1]);
                    isAdded = true;
                    indexAdded = j;
                } else if (listOfPairsSets.get(j).contains(pairs[i][1])) {
                    if (isAdded) {
                        listOfPairsSets.get(indexAdded).addAll(listOfPairsSets.get(j));
                        listOfPairsSets.remove(j);
                        break;
                    }
                    isAdded = true;
                    listOfPairsSets.get(j).add(pairs[i][0]);
                    indexAdded = j;
                }
            }
            if (!isAdded) {
                listOfPairsSets.add(new HashSet<>());
                listOfPairsSets.get(listOfPairsSets.size() - 1).add(pairs[i][0]);
                listOfPairsSets.get(listOfPairsSets.size() - 1).add(pairs[i][1]);
            }
        }

        StringBuilder sb = new StringBuilder(new String(new char[str.length()]).replace('\0', '_'));
        for (int i = 0; i < listOfPairsSets.size(); i++) {
            Integer[] indexesSet = Arrays.stream(listOfPairsSets.get(i).stream().mapToInt(Number::intValue).toArray()).boxed().toArray(Integer[]::new);
            Arrays.sort(indexesSet, Collections.reverseOrder());
            StringBuilder buf = new StringBuilder();
            for (int j = 0; j < indexesSet.length; j++) {
                buf.append(str.charAt(indexesSet[j]-1));
            }
            char[] bufArr = buf.toString().toCharArray();
            Arrays.sort(bufArr);
            for (int j = 0; j < indexesSet.length; j++) {
                sb.setCharAt(indexesSet[j]-1, bufArr[j]);
            }
        }
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '_') {
                sb.setCharAt(i, str.charAt(i));
            }
        }
        return sb.toString();
    }


    @Test
    public void testAllShifts() {
        int[] array = {1,2,3,4};
        /*for (int i = 0; i < array.length; i++) {

        }*/
        int[][] pAr = getAllShifts(array);

        String str = "abcd";
        String[] shifts = getAllShifts(str);
        ;
    }

    public int[][] getAllShifts(int[] array) {
        if (array.length == 1) return new int[][]{{array[0]}};
        int[][] bufArray = getAllShifts(Arrays.copyOf(array, array.length - 1));
        int[][] bufArrayOut = new int[bufArray.length*array.length][array.length];
        for (int i = 0; i < bufArray.length*array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int splitPoint = i / bufArray.length;
                int rowOfBuf = i % bufArray.length;
                if (splitPoint == j)
                    bufArrayOut[i][j] = array[array.length - 1];
                else if (splitPoint > j)
                    bufArrayOut[i][j] = bufArray[rowOfBuf][j];
                else if (splitPoint < j)
                    bufArrayOut[i][j] = bufArray[rowOfBuf][j - 1];
            }
        }
        //new int[array.length][]
        return bufArrayOut;
    }

    public String[] getAllShifts(String array) {
        if (array.length() == 1) return new String[]{array};
        String[] bufArray = getAllShifts(array.substring(1));
        String[] bufArrayOut = new String[bufArray.length*array.length()];
        for (int i = 0; i < bufArray.length*array.length(); i++) {
            StringBuilder strBuild = new StringBuilder();
            for (int j = 0; j < array.length(); j++) {
                int splitPoint = i / bufArray.length;
                int rowOfBuf = i % bufArray.length;
                if (splitPoint == j)
                    strBuild.append(array.charAt(0));
                    //bufArrayOut[i][j] = array.charAt(0);
                else if (splitPoint > j)
                    strBuild.append(bufArray[rowOfBuf].charAt(j));
                    //bufArrayOut[i][j] = bufArray[rowOfBuf][j];
                else if (splitPoint < j)
                    strBuild.append(bufArray[rowOfBuf].charAt(j - 1));
                    //bufArrayOut[i][j] = bufArray[rowOfBuf][j - 1];
            }
            bufArrayOut[i] = strBuild.toString();
        }
        return bufArrayOut;
    }
}
