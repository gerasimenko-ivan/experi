package linkedList;

public class ListNode2<T> {
    public T value;
    public ListNode2<T> next;
    ListNode2() {
    }
    ListNode2(T x) {
        value = x;
    }
    ListNode2(T[] xArr) {
        if (xArr == null) return;
        if (xArr.length == 0) return;
        if (xArr.length == 1) {
            value = xArr[0];
            return;
        }
        value = xArr[0];
        next = new ListNode2(xArr[1]);
        ListNode2<T> buf = next;
        for (int i = 2; i < xArr.length; i++) {
            buf.next = new ListNode2<>(xArr[i]);
            buf = buf.next;
        }
    }

    public ListNode2<T> addNode(T x) {
        ListNode2<T> buf = this;
        do {
            if (buf.next == null) {
                buf.next = new ListNode2<>(x);
                return this;
            }
            buf = buf.next;
        } while (true);
    }

    public ListNode2<T> removeNode(int k) {
        if (next == null) return null;
        if (k == 0) return next;
        ListNode2<T> buf = this;
        ListNode2<T> bufNext = next;
        int index = 1;
        do {
            index++;
        } while(index <= k);
        return null;
    }

    @Override
    public String toString() {
        String outLine = "";
        ListNode2<T> buf = this;
        do {
            outLine += (buf.value == null ? "NULL" : buf.value.toString()) + "; ";
            buf = buf.next;
        } while (buf != null);
        return outLine;
    }
}
