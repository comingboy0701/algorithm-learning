package test06_链表;


public class test01_reorderList {
    public static void main(String[] args) {
        int[] num = {1,2,3,4,5,6};
        ListNode link = new ListNode(0);
        for (int i : num) {
            link.add(i);
        }
        link = link.next;
        System.out.println(link.toString());

        int[] num2 = {7,8,9,10};
        ListNode link2 = new ListNode(0);
        for (int i : num2) {
            link2.add(i);
        }
        link2 = link2.next;
        System.out.println(link2.toString());


        // 链表的中间节点
        ListNode middle = middleNode(link);
        System.out.println(middle.toString());

        // 合并链表
        ListNode leftRight = merge(link,link2);
        System.out.println(leftRight.toString());

        // 反转链表
        ListNode reverseLink = reverse(link);
        System.out.println(reverseLink.toString());

        // 重排链表
        ListNode reorderLink = reorderList(reverseLink);
        System.out.println(reorderLink);

    }
    static public ListNode reorderList(ListNode head){
        if (head==null||head.next==null) return head;
        ListNode  middle = middleNode(head);
        ListNode right = middle.next;
        middle.next = null;
        ListNode left = head;
        right = reverse(right);
        ListNode res = merge(left,right);
        return res;
    }

    static public ListNode middleNode(ListNode head){
        if (head==null||head.next==null) return  head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static public ListNode reverse(ListNode head){
        if (head==null||head.next==null) return  head;
        ListNode last = reverse(head.next);
        head.next.next= head;
        head.next =null;
        return  last;
    }

    static public ListNode merge(ListNode left,ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        ListNode leftTmp;
        ListNode rightTmp;
        while (left != null && right!= null) {
            leftTmp = left.next;
            rightTmp = right.next;
            head.next =left;
            left.next = right;
            head = right;
            left = leftTmp;
            right = rightTmp;
        }
        if (left!=null)head.next = left;
        if (right!=null) head.next = right;
        return dummy.next;
    }
}
