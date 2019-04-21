package password_breaker;

import static java.util.Objects.isNull;

public class Password {
    private char currentLetter = 'a';
    private Password next;

    public Password() {
    }


    private String getCurrentLetter() {
        return (isNull(next) ? "" : next.getCurrentLetter()) + currentLetter;
    }

    String getPassword() {
        String current;

        current = "" + (currentLetter++);
        if (currentLetter > 'z') {
            currentLetter = 'a';
            if (isNull(next)) {
                next = new Password();
            } else {
                current += next.getPassword();
            }
        } else {
            current += (isNull(next) ? "" : next.getCurrentLetter());
        }
        return current;
    }
}
