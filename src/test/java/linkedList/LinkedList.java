package linkedList;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedList {

    @Test
    public void test() {
        Double d = 23434.65;
        System.out.println(String.format("%,.2f", d));
    }

    @Test
    public void test1() {
        ListNode<Integer> linkedList = new ListNode<>(3);
        linkedList.next = new ListNode<>(1);
        linkedList.next.next = new ListNode<>(3);
        linkedList.next.next.next = new ListNode<>(3);
        linkedList.next.next.next.next = new ListNode<>(4);
        linkedList.next.next.next.next.next = new ListNode<>(3);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());

        linkedList = removeKFromList(linkedList, 3);

        System.out.println(linkedList == null ? "[]" : linkedList.toString());

        linkedList = insertAtKToList(linkedList, 1, 10);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = insertAtKToList(linkedList, 1, 20);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = insertAtKToList(linkedList, 0, 0);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = insertAtKToList(linkedList, 5, 666);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());

        linkedList = deleteAtKFromList(linkedList, 5);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = deleteAtKFromList(linkedList, 0);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = deleteAtKFromList(linkedList, 1);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = deleteAtKFromList(linkedList, 1);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = deleteAtKFromList(linkedList, 1);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = deleteAtKFromList(linkedList, 0);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());

        linkedList = new ListNode<Integer>(1000);
        linkedList.next = new ListNode<Integer>(1000);

        System.out.println(linkedList == null ? "[]" : linkedList.toString());
        linkedList = removeKFromList(linkedList, 1000);
        System.out.println(linkedList == null ? "[]" : linkedList.toString());


    }

    ListNode<Integer> removeKFromList(ListNode<Integer> l, int k) {
        if (l == null) return null;
        ListNode<Integer> tempCurrentNode = l;
        l = null;
        ListNode<Integer> tempPreviuosNode = new ListNode<>();
        do {
            if (tempCurrentNode.value == k) {
                if (tempCurrentNode.next != null) {
                    // if there is a VAL to delete, we
                    //tempCurrentNode = tempCurrentNode.next; //tempCurrentNode - contains a link to tempCurrentNode.next : so I'd expect tempCurrentNode.next NOW points to tempCurrentNode.next.next

                    // What is the difference??? THERE IS DEFF: This will affect all links of tempCurrentNode, e.g. l
                    tempCurrentNode.value = tempCurrentNode.next.value;
                    tempCurrentNode.next = tempCurrentNode.next.next;   //*/
                } else {
                    tempPreviuosNode.next = null;
                    tempCurrentNode = null;
                }
            } else {
                if (l == null) {
                    l = tempCurrentNode;
                }
                tempPreviuosNode = tempCurrentNode;
                // Why this does NOT work????!!!! Because here we have created standalone object and define it
                /*tempPreviuosNode.value = tempCurrentNode.value;
                tempPreviuosNode.next = tempCurrentNode.next;   //*/
                tempCurrentNode = tempCurrentNode.next;
            }
        } while (tempCurrentNode != null);
        return l;
    }

    ListNode<Integer> insertAtKToList(ListNode<Integer> l, int k, Integer val) {
        int index = 0;
        ListNode<Integer> bufLink = l;
        ListNode<Integer> bufLink2 = new ListNode<>();
        do {
            if (index == (k - 1) && bufLink.next == null) {
                bufLink.next = new ListNode<>(val);
                break;
            } else if (index < k) {
                bufLink = bufLink.next;
            } else if (index == k) {
                bufLink2.value = bufLink.value;
                bufLink2.next = bufLink.next;
                bufLink.value = val;
                bufLink.next = bufLink2;
                break;
            }
            index++;
        } while (bufLink != null);
        return l;
    }

    ListNode<Integer> deleteAtKFromList(ListNode<Integer> l, int k) {
        int index = 0;
        ListNode<Integer> tempCurrentNode = l;
        ListNode<Integer> tempPreviousNode = null;
        do {
            if (index == k) {
                if (tempCurrentNode.next != null) {
                    tempCurrentNode.value = tempCurrentNode.next.value;
                    tempCurrentNode.next = tempCurrentNode.next.next;
                    break;
                } else {
                    if (index == 0) {
                        return null;
                    } else {
                        tempPreviousNode.next = null;
                        break;
                    }
                }
            }
            tempPreviousNode = tempCurrentNode;
            tempCurrentNode = tempCurrentNode.next;
            index++;
        } while (index <= k);
        return l;
    }



    @Test
    public void testPalindrome() {
        ListNode<Integer> linkedList = new ListNode<>(0);
        linkedList.next = new ListNode<>(1);
        linkedList.next.next = new ListNode<>(0);

        Assert.assertTrue(isListPalindrome(linkedList));

        linkedList = null;
        Assert.assertTrue(isListPalindrome(linkedList));


        linkedList = new ListNode<>(1);
        linkedList.next = new ListNode<>(2);
        linkedList.next.next = new ListNode<>(2);
        linkedList.next.next.next = new ListNode<>(3);

        Assert.assertFalse(isListPalindrome(linkedList));


        linkedList = new ListNode<>(1);
        linkedList.next = new ListNode<>(1000000000);
        linkedList.next.next = new ListNode<>(-1000000000);
        linkedList.next.next.next = new ListNode<>(-1000000000);
        linkedList.next.next.next.next = new ListNode<>(1000000000);
        linkedList.next.next.next.next.next = new ListNode<>(1);

        Assert.assertTrue(isListPalindrome(linkedList));
    }

    boolean isListPalindrome(ListNode<Integer> l) {
        if (l == null) return true;
        if (l.next == null) return true;
        ListNode<Integer> tempCurrentNode = l;
        int index = 0;
        do {
            tempCurrentNode = tempCurrentNode.next;
            index++;
        } while (tempCurrentNode.next != null);
        int length = index + 1;

        index = 1;
        tempCurrentNode = l.next;
        ListNode<Integer> tempPreviousNode = l;
        tempPreviousNode.next = null;   // now first becomes last element
        ListNode<Integer> tempNextNode;
        while (index < length/2) {
            tempNextNode = tempCurrentNode.next;
            tempCurrentNode.next = tempPreviousNode;
            tempPreviousNode = tempCurrentNode;
            tempCurrentNode = tempNextNode;
            index++;
        }

        if (length%2 == 1) {
            tempCurrentNode = tempCurrentNode.next;
        }

        index = 0;
        do {
            if (tempPreviousNode.value.intValue() != tempCurrentNode.value.intValue()) return false;
            tempCurrentNode = tempCurrentNode.next;
            tempPreviousNode = tempPreviousNode.next;
            index++;
        } while (index < length/2);
        return true;
    }


    @Test
    public void twoHugeNumbers() {
        //[9876, 5432, 1999]
        ListNode<Integer> a = new ListNode<>(9876);
        a.next = new ListNode<>(5432);
        a.next.next = new ListNode<>(1999);
        //[1, 8001]
        ListNode<Integer> b = new ListNode<>(1);
        b.next = new ListNode<>(8001);

        //[9876, 5434, 0]
        ListNode<Integer> c = new ListNode<>(9876);
        c.next = new ListNode<>(5434);
        c.next.next = new ListNode<>(0);

        ListNode<Integer> aPlusB = addTwoHugeNumbers(a, b);
        Assert.assertEquals(c.toString(), aPlusB.toString());
    }

    ListNode<Integer> addTwoHugeNumbers(ListNode<Integer> a, ListNode<Integer> b) {
        if (a == null) return b;
        if (b == null) return a;

        a = reverseList(a);
        ListNode<Integer> aReversedStart = a;
        b = reverseList(b);
        ListNode<Integer> bReversedStart = b;

        ListNode<Integer> sum = new ListNode<>(0);
        ListNode<Integer> sumHead = sum;
        ListNode<Integer> sumPrevious = new ListNode<>(0);
        int overPackSum = 0;
        do {
            int aVal, bVal;
            if (a == null) {
                aVal = 0;
            } else {
                aVal = a.value;
            }
            if (b == null) {
                bVal = 0;
            } else {
                bVal = b.value;
            }
            int packSum = aVal + bVal + sum.value;
            overPackSum = packSum/10000;
            sum.value = packSum%10000;
            sum.next = new ListNode<>(overPackSum);
            sumPrevious = sum;
            sum = sum.next;
            if (a != null) a = a.next;
            if (b != null) b = b.next;
        } while (b != null || a != null);
        if (sum.value == 0) {
            sumPrevious.next = null;
        }
        a = reverseList(aReversedStart);
        b = reverseList(bReversedStart);
        return reverseList(sumHead);
    }

    ListNode<Integer> reverseList(ListNode<Integer> listNode) {
        if (listNode == null) return null;
        if (listNode.next == null) return listNode;
        ListNode<Integer> previousNode = listNode;
        listNode = listNode.next;
        ListNode<Integer> nextNode;
        previousNode.next = null;
        do {
            nextNode = listNode.next;
            listNode.next = previousNode;
            previousNode = listNode;
            listNode = nextNode;

        } while (listNode != null);
        return previousNode;
    }

    @Test
    public void mergeListsTest() {
        ListNode<Integer> a = new ListNode<>(new Integer[]{1, 1, 2, 4});
        ListNode<Integer> b = new ListNode<>(new Integer[]{0, 3, 5});

        ListNode<Integer> cExp = new ListNode<>(new Integer[]{0, 1, 1, 2, 3, 4, 5});
        ListNode<Integer> c = mergeTwoLinkedLists(a, b);// = [1, 2, 3, 4, 5, 6];
        Assert.assertEquals(c, cExp);

    }

    ListNode<Integer> mergeTwoLinkedLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode<Integer> merged = new ListNode<>(0);
        ListNode<Integer> mergedHead = merged;
        do {
            if (l1 != null && (l2 == null || l1.value < l2.value)) { // + l2 == null
                merged.value = l1.value;
                l1 = l1.next;
            } else { // + l1 == null
                merged.value = l2.value;
                l2 = l2.next;
            }
            if (l1 != null || l2 != null) {
                merged.next = new ListNode<>(0);
                merged = merged.next;
            } else {
                break;
            }
        } while (true);
        return mergedHead;
    }


    @Test
    public void reverseNodesInGroupsTest() {
        ListNode<Integer> a = new ListNode<>(new Integer[]{1, 2, 3, 4, 5});
        int k = 2;
        ListNode<Integer> reverseExpected = new ListNode<>(new Integer[]{2, 1, 4, 3, 5});
        ListNode<Integer> reverseA = reverseNodesInKGroups(a, k);
        Assert.assertEquals(reverseA, reverseExpected);

        /*a = new ListNode<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        k = 3;
        reverseExpected = new ListNode<>(new Integer[]{3, 2, 1, 6, 5, 4, 9, 8, 7, 10, 11});
        reverseA = reverseNodesInKGroups(a, k);
        Assert.assertEquals(reverseA, reverseExpected);*/

        a = new ListNode<>(new Integer[]{1, 2, 3, 4});
        k = 2;
        reverseExpected = new ListNode<>(new Integer[] {2, 1, 4, 3});
        reverseA = reverseNodesInKGroups(a, k);
        Assert.assertEquals(reverseA, reverseExpected);
    }

    ListNode<Integer> reverseNodesInKGroups(ListNode<Integer> l, int k) {
        if (k == 1) return l;
        if (l == null) return null;
        if (l.next == null) return l;

        ListNode<Integer> newListHead = new ListNode<>(0);
        ListNode<Integer> kGroupStartOdd = l;
        ListNode<Integer> kGroupStartEven = new ListNode<>(0);
        ListNode<Integer> previousNode = l;
        ListNode<Integer> currentNode = l.next;
        l.next = null;
        ListNode<Integer> currentNodeNext = null;
        int index = 1;
        do {
            if (index == k-1) {
                newListHead = currentNode;
            }
            if ((index % (2*k)) == 0) { // start of ODD group
                kGroupStartOdd = currentNode;
            }
            if ((index % (2*k)) == k) { // start of EVEN group
                kGroupStartEven = currentNode;
            }
            if ((index % (2*k)) == (2*k - 1)) { // end of EVEN group
                if (currentNode.next != null)
                    currentNodeNext = currentNode.next;
                else
                    currentNodeNext = null;
                kGroupStartOdd.next = currentNode;
                currentNode.next = previousNode;
                if (currentNodeNext != null)
                    currentNode = currentNodeNext;
                else
                    return newListHead;
                index++;
                continue;
            }
            if ((index % (2*k)) == (k - 1) && index != k-1) { // end of ODD group
                if (currentNode.next != null)
                    currentNodeNext = currentNode.next;
                else
                    currentNodeNext = null;
                kGroupStartEven.next = currentNode;
                currentNode.next = previousNode;
                if (currentNodeNext != null)
                    currentNode = currentNodeNext;
                else
                    return newListHead;
                index++;
                continue;
            }
            currentNodeNext = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = currentNodeNext;

            kGroupStartOdd.next = null;
            kGroupStartEven.next = null;
            index++;
        } while (currentNode != null);

        if (index%k == 1) {
            if (kGroupStartEven == previousNode) {
                kGroupStartOdd.next = previousNode;
            }
            if (kGroupStartOdd == previousNode) {
                kGroupStartEven.next = previousNode;
            }
        } else {
            ListNode<Integer> reverseList = reverseList(previousNode);
            if (kGroupStartEven == reverseList) {
                kGroupStartOdd.next = reverseList;
            }
            if (kGroupStartOdd == reverseList) {
                kGroupStartEven.next = reverseList;
            }
        }

        return newListHead;
    }


    @Test
    public void testRearrangingOfLastN() {
        ListNode<Integer> a = new ListNode<>(new Integer[]{1, 2, 3, 4, 5});
        int n = 2;
        ListNode<Integer> aRearrExp = new ListNode<>(new Integer[]{4, 5, 1, 2, 3});
        ListNode<Integer> aRearr = rearrangeLastN(a, n);
        Assert.assertEquals(aRearr, aRearrExp);

        a = new ListNode<>(new Integer[]{1, 2});
        n = 1;
        aRearrExp = new ListNode<>(new Integer[]{2, 1});
        aRearr = rearrangeLastN(a, n);
        Assert.assertEquals(aRearr, aRearrExp);
    }

    ListNode<Integer> rearrangeLastN(ListNode<Integer> l, int n) {
        if (l == null) return null;
        if (n == 0) return l;
        if (l.next == null) return l;

        int index = 0;
        int totalLength = 0;
        ListNode<Integer> currentNode = l;
        do {
            currentNode = currentNode.next;
            index++;
        } while (currentNode.next != null);
        totalLength = index + 1;
        ListNode<Integer> lastNode = currentNode;

        if (totalLength == n) return l;

        index = 0;
        currentNode = l;
        while (index < totalLength - n - 1) {
            currentNode = currentNode.next;
            index++;
        }
        ListNode<Integer> newHead = currentNode.next;
        currentNode.next = null; // new tail
        lastNode.next = l;

        return newHead;
    }
}
