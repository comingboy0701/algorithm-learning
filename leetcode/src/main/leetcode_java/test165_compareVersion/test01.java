package test165_compareVersion;

public class test01 {
    public static void main(String[] args) {
        String version1 = "0.1";
        String version2 = "1.1";
        int result = new test01().compareVersion(version1,version2);
        System.out.println(result);
    }

    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");
        int len1 = ver1.length;
        int len2 = ver2.length;
        int len = Math.max(len1,len2);
        for (int i = 0; i < len; i++) {
            int num1 = 0;
            int num2 = 0;
            if (i<len1){
                num1 = Integer.parseInt(ver1[i]);
            }
            if (i<len2){
                num2 = Integer.parseInt(ver2[i]);
            }
            if (num1<num2) {
                return -1;
            }else if(num1>num2){
                return 1;
            }
        }
        return 0;
    }

}
