package linkedList;

import org.testng.annotations.Test;

public class ListNode2Test {
    @Test
    public void test1() {
        ListNode2<String> a0 = new ListNode2<>();
        System.out.println(a0);
        ListNode2<String> aN = new ListNode2<>((String)null);
        System.out.println(aN);
        ListNode2<String> a1 = new ListNode2<>("a");
        System.out.println(a1);
        ListNode2<String> a2 = new ListNode2<>(new String[]{"a", "b"});
        System.out.println(a2);
        ListNode2<String> a22 = new ListNode2<>("a");
        a22.next = new ListNode2<>("b");
        System.out.println(a22);
        ListNode2<String> a3 = new ListNode2<>(new String[]{"a", "b", "c"});
        System.out.println(a3);
        ListNode2<String> a3N = new ListNode2<>(new String[]{"a", null, "c"});
        System.out.println(a3N);
    }

    @Test
    public void test2() {
        ListNode2<String> a0 = new ListNode2<>();
        a0.addNode("A");
        System.out.println(a0);

        ListNode2<String> a1 = new ListNode2<>("a");
        System.out.println(a1.addNode("A"));

        ListNode2<String> a3N = new ListNode2<>(new String[]{"a", null, "c"});
        System.out.println(a3N.addNode("A"));
    }
}
