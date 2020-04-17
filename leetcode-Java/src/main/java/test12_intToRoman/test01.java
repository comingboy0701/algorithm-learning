package test12_intToRoman;

public class test01 {
    public static void main(String[] args) {
        int num = 16;
        String result = intToRoman(num);
        System.out.println(result);
    }
    public static String intToRoman(int num){
        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder stringBuilder = new StringBuilder();
        while (num>=1){
            int index = 0;
            while (true){
                if (num>=nums[index]){
                    num = num-nums[index];
                    stringBuilder.append(romans[index]);
                    break;
                }
                index++;
            }
        }
        return stringBuilder.toString();
    }

}
