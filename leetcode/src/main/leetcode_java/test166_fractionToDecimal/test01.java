package test166_fractionToDecimal;

import java.util.HashMap;

public class test01 {
    public static void main(String[] args) {
        int numerator = 0;
        int denominator = 6;
        String result = new test01().fractionToDecimal(numerator,denominator);
        System.out.println(result);

    }


    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator==0){
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        if (numerator<0^denominator<0){
            fraction.append("-");
        }
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend/divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        HashMap<Long,Integer> map = new HashMap<>();
        while (remainder!=0){
            map.put(remainder,fraction.length());
            remainder = remainder*10;
            fraction.append(String.valueOf(remainder/divisor));
            remainder = remainder%divisor;
            if (map.containsKey(remainder)){
                fraction.insert(map.get(remainder),"(");
                fraction.append(")");
               break;
            }
        }
        return fraction.toString();
    }
}
