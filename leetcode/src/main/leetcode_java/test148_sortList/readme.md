# leetcode-148 排序链表
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

### 1 递归排序
![](../../../resource/leetcode148_sortList.png)

```Java
class Solution {
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
```


