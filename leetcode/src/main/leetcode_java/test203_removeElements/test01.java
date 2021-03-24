package test203_removeElements;

public class test01 {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        ListNode res = new test01().removeElements(head, 1);
        while (res!=null){
            System.out.println(res.val);
            res = res.next;
        }

    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode curr = head;
        while(curr!=null){
            if(curr.val==val){
                pre.next = curr.next;
            }else{
                pre = curr;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
