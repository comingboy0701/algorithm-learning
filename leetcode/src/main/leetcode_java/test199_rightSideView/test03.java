package test199_rightSideView;

import java.util.*;

public class test03 {
    
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        treeNode.left.left = new TreeNode(0);

        List<Integer> res = new test03().rightSideView(treeNode);
        System.out.println(res);
    }
    List<Integer> res = new ArrayList<>();
    
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root,0);
        return res;
    }

    private void dfs(TreeNode root, int depth){
        if (root==null){
            return;
        }
        if (depth==res.size()){
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}
