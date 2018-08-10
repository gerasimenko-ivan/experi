package trees;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    Tree(T x) {
        value = x;
    }
    T value;
    Tree<T> left;
    Tree<T> right;

    final static String RIGHT = "right";
    final static String LEFT = "left";
    final static String VALUE = "value";
    final static String NULL = "null";

    public static Tree<Integer> getTreeOfIntegers(String jsonTreeView) {
        List<Tree<Integer>> nodesNext = new ArrayList<>();
        JSONObject treeJSON = new JSONObject(jsonTreeView);
        List<JSONObject> nodesJson = new ArrayList<>();
        List<JSONObject> nodesJsonNext = new ArrayList<>();
        List<Tree<Integer>> nodes = new ArrayList<>();
        Tree<Integer> rootNode = new Tree<>(Integer.valueOf(treeJSON.get(VALUE).toString()));
        Tree<Integer> node;
        Tree<Integer> nodeNext;
        if (!treeJSON.get(LEFT).toString().equals(NULL)) {
            JSONObject leftNodeJson = (JSONObject) treeJSON.get(LEFT);
            nodesJson.add(leftNodeJson);
            node = new Tree<>(Integer.valueOf(leftNodeJson.get(VALUE).toString()));
            rootNode.left = node;
            nodes.add(node);
        }
        if (!treeJSON.get(RIGHT).toString().equals(NULL)) {
            JSONObject rightNodeJson = (JSONObject) treeJSON.get(RIGHT);
            nodesJson.add(rightNodeJson);
            node = new Tree<>(Integer.valueOf(rightNodeJson.get(VALUE).toString()));
            rootNode.right = node;
            nodes.add(node);
        }
        do {
            for (int i = 0; i < nodesJson.size(); i++) {
                JSONObject nodeJson = nodesJson.get(i);
                node = nodes.get(i);
                if (!nodeJson.get(LEFT).toString().equals(NULL)) {
                    JSONObject leftNodeJson = (JSONObject) nodeJson.get(LEFT);
                    nodesJsonNext.add(leftNodeJson);
                    nodeNext = new Tree<>(Integer.valueOf(leftNodeJson.get(VALUE).toString()));
                    node.left = nodeNext;
                    nodesNext.add(nodeNext);
                }
                if (!nodeJson.get(RIGHT).toString().equals(NULL)) {
                    JSONObject rightNodeJson = (JSONObject) nodeJson.get(RIGHT);
                    nodesJsonNext.add(rightNodeJson);
                    nodeNext = new Tree<>(Integer.valueOf(rightNodeJson.get(VALUE).toString()));
                    node.right = nodeNext;
                    nodesNext.add(nodeNext);
                }
            }
            nodes.clear();
            nodes.addAll(nodesNext);
            nodesNext.clear();

            nodesJson.clear();
            nodesJson.addAll(nodesJsonNext);
            nodesJsonNext.clear();
        } while (nodesJson.size() > 0);
        /*if (treeJSON.has(LEFT)) {

        }*/
        String tree = rootNode.getTree();
        return rootNode;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public String getTree() {
        return "";
    }
}
