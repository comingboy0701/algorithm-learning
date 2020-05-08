package test06_链表;

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

    public void addNode(ListNode newNode){
        if(this.next==null){
            this.next = newNode;
        }else{
            this.next.addNode(newNode);
        }
    }

    public void add(int num){
        ListNode newNode = new ListNode(num);
        if(this.next==null){
            this.next = newNode;
        }else{
            this.next.addNode(newNode);
        }
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
