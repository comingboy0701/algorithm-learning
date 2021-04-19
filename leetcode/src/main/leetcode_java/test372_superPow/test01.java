package test372_superPow;

public class test01 {
    public static void main(String[] args) {
        int[] b = {1,2,3};
        int a = 2;
        int res = new test01().superPow(a, b);
        System.out.println(res);
    }

    int base = 1337;
    public int superPow(int a, int[] b) {
        int len = b.length;
        int ans = indexPow(a, b, len);
        return ans;
    }

    //a的k次方对base取模
    private int myPow(int a,int k){
        a %=base;
        int ans=1;
        for(int i = 0;i<k;i++){
            ans *= a;
            ans %=base;
        }
        return ans;
    }
    //加入index判断是否需要终止递归
    private int indexPow(int a,int[] b,int index){
        if(index < 1 )return 1;

        int part1 = myPow(a, b[index-1]);
        index -- ;
        int part2 = myPow(indexPow(a, b, index), 10);

        return part1*part2%base;
    }
}
