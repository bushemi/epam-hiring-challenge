package brutForcer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PasswordTest {
    HashCalculator calculator = new HashCalculator();
    String target = "69c459dd76c6198f72f0c20ddd3c9447"; //zebra
    String targetAdvanced = "4fd0101ea3d0f5abbe296ef97f47afec";

    @Test
    public void findPasswordRegular() {
        Password password = new Password("one");
        String passwordPassword;
        while (true) {
            passwordPassword = password.getPassword();
            System.out.println("passwordPassword = " + passwordPassword);
            if (checkForMatch(passwordPassword)) break;
        }
        System.out.println("passwordPassword = " + passwordPassword);
    }

    @Test
    public void findPasswordAdvancedLevel() {
        Password password = new Password("one");
        String passwordPassword;
        while (true) {
            passwordPassword = password.getPassword();
            System.out.println("passwordPassword = " + passwordPassword);
            if (checkForMatchAdvanced(passwordPassword)) break;
        }
        System.out.println("passwordPassword = " + passwordPassword);
    }

    private boolean checkForMatch(String text) {
        String newHash = calculator.hash(text);
        return newHash.equals(target);
    }

    private boolean checkForMatchAdvanced(String text) {
        String newHash = calculator.hash(text);
        return newHash.equals(targetAdvanced);
    }

}
