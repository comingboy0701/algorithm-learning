package test04二叉树;

public class BinaryTreeNode {
    private int val;
    private BinaryTreeNode left; //默认null
    private BinaryTreeNode right; //默认null

    //有参构造方法定义
    public BinaryTreeNode(int val) {
        super();
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    //定义成员方法
    public void setVal(int val)//1.设置二叉树节点的值
    {
        this.val = val;
    }

    public void setLeft(BinaryTreeNode left)//2.设置节点为当前节点的左孩子
    {
        this.left = left;
    }

    public void setRight(BinaryTreeNode right)//3.设置节点为当前节点的右孩子
    {
        this.right = right;
    }

    public BinaryTreeNode getLeft()// 4.无参调用，返回值为左孩子，类型为BinaryTreeNode
    {
        return left;
    }

    public BinaryTreeNode getRight()//5.无参调用，返回值为右孩子，类型为BinaryTreeNode
    {
        return right;
    }

    @Override
    public String toString()//6.改写toString方法，打印节点编号时pringln使用
    {
        return "BinaryTreeNode[val=" + val + "]";
    }

    //7.前序遍历方法
    public void preOrder() {
        System.out.println(this);//先输出当前节点
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }//栈存储？ 怎么退回到上一层节点
    }




    //8.中序遍历方法
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this); //找不到左孩子节点就可以输出当前节点了
        if (this.right != null) {
            this.right.midOrder();//先从左子树开始找，左子树为空时，在从最底层的右子树开始向上找
        }
    }

    //9.后序遍历方法
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//能输出时说明前面两项均不满足，即当前节点既没有左节点又没有右节点
    }
}
