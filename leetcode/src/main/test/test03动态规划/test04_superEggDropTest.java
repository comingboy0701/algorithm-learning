package test03动态规划;

import org.junit.Test;

import static org.junit.Assert.*;

public class test04_superEggDropTest {
    @Test
    public void superEggDrop() throws Exception {
        int k =2, n=7;
        int min = new test04_superEggDrop().superEggDrop(k,n);
        System.out.println(min);
    }

}