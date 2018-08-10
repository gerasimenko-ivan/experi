package trees;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class treeTest {

    @Test
    public void hasPathWithGivenSumTest(){
        Tree<Integer> root =
                Tree.getTreeOfIntegers("{" +
                        "    'value': 4," +
                        "    'left': {" +
                        "        'value': 1," +
                        "        'left': {" +
                        "            'value': -2," +
                        "            'left': null," +
                        "            'right': {" +
                        "                'value': 3," +
                        "                'left': null," +
                        "                'right': null" +
                        "            }" +
                        "        }," +
                        "        'right': null" +
                        "    }," +
                        "    'right': {" +
                        "        'value': 3," +
                        "        'left': {" +
                        "            'value': 1," +
                        "            'left': null," +
                        "            'right': null" +
                        "        }," +
                        "        'right': {" +
                        "            'value': 2," +
                        "            'left': {" +
                        "                'value': -2," +
                        "                'left': null," +
                        "                'right': null" +
                        "            }," +
                        "            'right': {" +
                        "                'value': -3," +
                        "                'left': null," +
                        "                'right': null" +
                        "            }" +
                        "        }" +
                        "    }" +
                        "}");
        /*root.left = new Tree<>(1);
        root.left.left = new Tree<>(-2);
        root.left.left.right = new Tree<>(3);

        root.right = new Tree<>(3);
        root.right.left = new Tree<>(1);
        root.right.right = new Tree<>(2);
        root.right.right.left = new Tree<>(-2);
        root.right.right.right = new Tree<>(-3);*/

        boolean b = hasPathWithGivenSum(root, 7);
        boolean b2 = hasPathWithGivenSum(root, 100);
    }

    boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
        if (t == null) return false;
        List<Tree<Integer>> levelNodes = new ArrayList<>();
        levelNodes.add(t);
        List<Integer> levelSums = new ArrayList<>();
        List<Tree<Integer>> levelNodesNext = new ArrayList<>();
        List<Integer> levelSumsNext = new ArrayList<>();
        levelSums.add(t.value);
        do {
            for (int i = 0; i < levelNodes.size(); i++) {
                Tree<Integer> node = levelNodes.get(i);
                if (node.left == null && node.right == null && (levelSums.get(i)) == s)
                    return true;
                if (node.left != null) {
                    levelNodesNext.add(node.left);
                    levelSumsNext.add(levelSums.get(i) + node.left.value);
                }
                if (node.right != null) {
                    levelNodesNext.add(node.right);
                    levelSumsNext.add(levelSums.get(i) + node.right.value);
                }
            }
            levelNodes.clear();
            levelNodes.addAll(levelNodesNext);
            levelNodesNext.clear();
            levelSums.clear();
            levelSums.addAll(levelSumsNext);
            levelSumsNext.clear();
        } while (levelNodes.size() > 0);
        return false;
    }
}
