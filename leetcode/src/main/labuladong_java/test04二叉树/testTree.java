package test04二叉树;

public class testTree {
    public static void main(String[] args) {
        int[] num = {1,2,3};
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right= new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(root);
    }
}
