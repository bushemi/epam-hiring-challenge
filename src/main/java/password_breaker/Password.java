package password_breaker;

import static java.util.Objects.isNull;

public class Password {
    private char currentLetter = 'a';
    private Password next;
    private static long count = 0;

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
        showIfAllLettersAreZ(current);
        return current;
    }

    private void showIfAllLettersAreZ(String text) {
        if ((count++ % 100000) == 0) {
            System.out.println("count = " + count);
            System.gc();
        }
        for (char letter : text.toCharArray()) {
            if (letter != 'z') return;
        }
        System.out.println("text = " + text);
    }

}
