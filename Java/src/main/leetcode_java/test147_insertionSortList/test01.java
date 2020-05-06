package test147_insertionSortList;

public class test01 {
    public static void main(String[] args) {

            ListNode head= new ListNode(3);
            head.next= new ListNode(1);
            head.next.next= new ListNode(7);
            ListNode res = new test01().insertionSortList(head);
            System.out.println(res.toString());
    }

    public ListNode insertionSortList(ListNode head){
        if (head==null||head.next==null) return  head;
        ListNode dummy = new ListNode(-1);
        ListNode pre = head;
        ListNode cur = head.next;
        dummy.next = pre;
        while (cur!=null){
            if (pre.val<cur.val){
                cur =cur.next;
                pre = pre.next;
            }else {
                ListNode p = dummy;
                while (p.next!=null&&p.next.val<cur.val){
                    p = p.next;
                }
                ListNode tmp = p.next;
                pre.next = cur.next;
                cur.next = tmp;
                p.next = cur;
                cur = pre.next;
            }
        }
        return dummy.next;
    }

}
