package test04二叉树;


class BinaryTree {
    private BinaryTreeNode root;//定义根节点

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    //BinaryTree类前序遍历代码实现
    public void preOrder()//BinaryTree类中的preOrder方法调用了同一包下的BinaryTreeNode类中重名的preOrder()方法，下同
    {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //BinaryTree类的中序遍历代码实现
    public void minOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //BinaryTree类的后序遍历代码实现
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}
