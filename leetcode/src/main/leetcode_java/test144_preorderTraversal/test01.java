package test144_preorderTraversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class test01 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        left.left = right;
        root.left=left;
        List<Integer> res = new test01().preorderTraversal(root);
        System.out.println(res);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur  = root;
        if(root==null) return res;

        while (cur!=null || !stack.isEmpty()){
            while (cur!=null){
                res.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.pop();
            cur = tmp.right;
        }
        return res;
    };
}
