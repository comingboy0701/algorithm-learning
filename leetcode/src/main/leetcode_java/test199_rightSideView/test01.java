package test199_rightSideView;

import java.util.*;

public class test01 {
    
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        treeNode.left.left = new TreeNode(0);

        List<Integer> res = new test01().rightSideView(treeNode);
        System.out.println(res);
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> depthQueue = new LinkedList<Integer>();
        nodeQueue.add(root);
        depthQueue.add(0);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();
            
            if (node!=null){
                max_depth = Math.max(max_depth, depth);
                rightmostValueDepth.put(depth,node.val);
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth+1);
                depthQueue.add(depth+1);
            }
        }
    List<Integer> rightView = new ArrayList<>();
    for (int depth=0;depth<=max_depth;depth++){
        rightView.add(rightmostValueDepth.get(depth));
    }
    return rightView;
    }
}
