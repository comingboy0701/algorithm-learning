package test04二叉树;

public class test01_Traversal {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        //手动创建二叉树，测试
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        //有节点了，构建出二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node4.setLeft(node6);//其余节点默认为左子树或右子树为null
        //设置根节点
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        root.preOrder();

        System.out.println("中序遍历");
        root.midOrder();

        System.out.println("后序遍历");
        root.postOrder();

    }
}
