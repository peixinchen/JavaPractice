// BUG：程序死循环了

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this(val, null);
    }
}

public class LinkedListInterview {
    public ListNode separateByX(ListNode head, int x) {
        ListNode sHead = null;
        ListNode sEnd = null;
        ListNode bHead = null;
        ListNode bEnd = null;

        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (cur.val < x) {
                if (sHead == null) {
                    sHead = cur;
                } else {
                    sEnd.next = cur;
                }
                sEnd = cur;
            } else {
                if (bHead == null) {
                    bHead = cur;
                } else {
                    bEnd.next = cur;
                }
                bEnd = cur;
            }
        }

        if (sEnd == null) {
            return bHead;
        }

        sEnd.next = bHead;
        return sHead;
    }

    private static ListNode createTestList() {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(5);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(7);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(3);
        ListNode n7 = new ListNode(8);
        ListNode n8 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        return n1;
    }

    // 三个重要节点
    // 1. 咱创建的测试链表是不是出问题了？
    // 2. 咱的分离程序是不是出问题了？
    // 3. 咱的打印程序是不是出问题了？
    private static void test() {
        // 4 5 2 7 6 3 8 1
        ListNode head = createTestList();
        ListNode result = new LinkedListInterview().separateByX(head, 5);
        // 4 2 3 1 5 7 6 8
        for (ListNode cur = result; cur != null; cur = cur.next) {
            System.out.println(cur.val);
        }
    }

    public static void main(String[] args) {
        test();
    }
}