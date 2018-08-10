package linkedList;

public class ListNode<T> {
    ListNode() {
    }
    ListNode(T x) {
        value = x;
    }
    ListNode(T[] tArray) {
        if (tArray == null || tArray.length == 0) return;
        value = tArray[0];
        if (tArray.length < 2) return;
        next = new ListNode<T>(tArray[1]);
        ListNode<T> buf = next;
        for(int i = 1; i < tArray.length - 1; i++) {
            buf.value = tArray[i];
            buf.next = new ListNode<T>(tArray[i+1]);
            buf = buf.next;
        }
    }

   T value;
   ListNode<T> next;

   public ListNode<T> addNode(T x) {
       ListNode<T> buf = this;
       while (buf.next != null) {
           buf = buf.next;
       }
       buf.next = new ListNode<T>(x);
       return this;
   }

    @Override
    public String toString() {
        String output = "";
        ListNode<T> link = this;
        do {
            output += (link.value + "; ");
            link = link.next;
        } while (link != null);
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
