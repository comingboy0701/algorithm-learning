package test21_mergeTwoLists;

public class test01 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head= new ListNode(0);
        ListNode dummy = head;

        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                head.next = new ListNode(l1.val);
                l1=l1.next;
            }else {
                head.next = new ListNode(l2.val);
                l2=l2.next;
            }
            head=head.next;
        }
        if (l1!=null) head.next=l1;
        if (l2!=null) head.next=l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next=new ListNode(2);
        l1.next.next=new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next=new ListNode(3);
        l2.next.next=new ListNode(4);
        test01  sol = new test01();
        System.out.println(sol.mergeTwoLists(l1,l2));

    }

}
