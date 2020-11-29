package test03动态规划;

import org.junit.Test;

public class test06_coinChangeTest {
    @Test
    public void coinChange() throws Exception {
        int[] coin = {1, 2, 3};
        int amount = 9;
        int result = new test06_coinChange().coinChange(coin, amount);
        System.out.println(result);
    }

}