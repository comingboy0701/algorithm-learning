package test202_isHappy;

public class test01 {
    public static void main(String[] args) {
        boolean res = new test01().isHappy(2);
        System.out.println(res);

    }
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getnext(n);
        while (fast!=1 && slow!=fast){
            slow = getnext(slow);
            fast = getnext(getnext(fast));
        }
        return fast==1;

    }

    public int getnext(int n){
        int sum = 0;
        while (n>0){
            int mod = n%10;
            n = n/10;
            sum += mod*mod;
        }
        return sum;
    }

}
