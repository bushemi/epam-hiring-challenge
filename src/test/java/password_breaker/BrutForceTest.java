package password_breaker;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class BrutForceTest {

    @Test
    public void testFindPasswordRegular() throws Exception {
        BrutForce bf = new BrutForce(4);
        System.out.println("start");
        long before = System.currentTimeMillis();
        bf.findPasswordRegular();
        long after = System.currentTimeMillis();
        long spentMillis = after - before;
        System.out.println("finish");
        System.out.println("spentMillis = " + spentMillis);
    }

    @Test
    public void findPasswordAdvancedLevel() throws Exception {
        BrutForce bf = new BrutForce(4);
        System.out.println("start");
        long before = System.currentTimeMillis();
        bf.findPasswordAdvancedLevel();
        long after = System.currentTimeMillis();
        long spentMillis = after - before;
        System.out.println("finish");
        System.out.println("spentMillis = " + spentMillis);
    }


}
