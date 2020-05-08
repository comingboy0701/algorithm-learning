package test148_sortList;

public class test01 {
    public static void main(String[] args) {
        ListNode head= new ListNode(3);
        head.next= new ListNode(1);
        head.next.next= new ListNode(7);
        ListNode res = new test01().sortList(head);
        System.out.println(res.toString());
    }


    public ListNode sortList(ListNode head) {
        if(head==null ||head.next==null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null&&fast.next!=null){
            slow =slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode h = new ListNode(-1);
        ListNode res = h;
        while(left!=null&&right!=null){
            if(left.val<right.val){
                h.next = left;
                left = left.next;
            }else{
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next= left!=null?left:right;
        return res.next;
    }
}
