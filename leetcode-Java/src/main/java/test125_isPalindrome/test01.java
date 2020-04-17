package test125_isPalindrome;

public class test01 {
    public static void main(String[] args) {
        String a = "A man, a plan, a canal: Panama";
//        boolean res = isPalindrome(a);
//        System.out.println(res);
        for (int i = 0; i < a.length(); i++) {
            Character s = a.charAt(i);
            System.out.println(s+":"+Character.isLetterOrDigit(s));
        }


    }
    static public boolean isPalindrome(String s) {
        s = s.toUpperCase();
        int right = s.length()-1;
        int left = 0;
        while(left<right){
            int numLeft = s.charAt(left);
            if (!isValid(numLeft)){
                left++;
                continue;
            }
            int numRight = s.charAt(right);
            if (!isValid(numRight)){
                right--;
                continue;
            }
            if(numLeft==numRight){
                left++;
                right--;
            }else{
                return false;
            }
        }
        return true;
    }
    static public boolean isValid(int s){
        if((s>=65&&s<=90)||(s>=48&&s<=57)){
            return true;
        }else{
            return false;
        }
    }

}
