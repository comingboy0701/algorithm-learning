package test144_preorderTraversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        return dp(root);
    };
    private List<Integer> dp(TreeNode root){
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) return res;
        q.add(root);

        while (!q.isEmpty()){
            TreeNode tmp = q.poll();
            res.add(tmp.val);
            if(tmp.right!=null){
                q.add(root.right);
            }
            if (tmp.left!=null){
                q.add(root.left);
            }
        }
        return res;
    }
}
