package test201_rangeBitwiseAnd;

public class test01 {
    public static void main(String[] args) {
        int left = 11;
        int right = 11;
        int res =  new test01().rangeBitwiseAnd(left, right);
        System.out.println(res);

    }
    public int rangeBitwiseAnd(int left, int right) {
        int shift=0;
        while (left!=right){
            left >>=1;
            right >>=1;
            shift++;
        }
        return left<<shift;
    }
}
