package brutForcer;

import static java.util.Objects.isNull;

public class Password {
    private char currentLetter = 'a';
    private Password next;
    private String name;

    public Password() {
    }

    public Password(String name) {
        this.name = name;
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
                next = new Password(name + ":" + current);
            } else {
                current += next.getPassword();
            }
        } else {
            current += (isNull(next) ? "" : next.getCurrentLetter());
        }
        return current;
    }
}
