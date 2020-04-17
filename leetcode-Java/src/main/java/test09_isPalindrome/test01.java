package test09_isPalindrome;

public class test01 {
    public static void main(String[] args) {
        int x = 123321;
        System.out.println(isPalindrome(x));
    }
    public static Boolean isPalindrome(int x){
        if (x<0 || (x%10==0&&x!=0)) return false;
        int revertedNmuber = 0;
        while (x>revertedNmuber){
            revertedNmuber = revertedNmuber*10+x%10;
            x = x/10;
        }
        return x==revertedNmuber || x==revertedNmuber/10;
    }
}
