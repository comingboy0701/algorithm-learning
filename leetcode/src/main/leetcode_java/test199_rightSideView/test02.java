package test199_rightSideView;

import java.util.*;

public class test02 {
    
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        treeNode.left.left = new TreeNode(0);

        List<Integer> res = new test02().rightSideView(treeNode);
        System.out.println(res);
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(0);
        while (!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();
            
            if (node!=null){
                max_depth = Math.max(max_depth, depth);
                if (!rightmostValueDepth.containsKey(max_depth)){
                    rightmostValueDepth.put(depth,node.val);
                }
                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth+1);
                depthStack.push(depth+1);
            }
        }
    List<Integer> rightView = new ArrayList<>();
    for (int depth=0;depth<=max_depth;depth++){
        rightView.add(rightmostValueDepth.get(depth));
    }
    return rightView;
    }
}
