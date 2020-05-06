package test148_sortList;

public class ListNode {
    int val;
    ListNode next;
    public ListNode (int val,ListNode next){
        this.val = val;
        this.next =next;
    }
    public ListNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        ListNode head= this;
        StringBuilder str = new StringBuilder();
        while (head!=null){
            str.append(head.val+"  ");
            head = head.next;
        }
        return str.toString();
    }
}