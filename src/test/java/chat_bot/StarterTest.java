package chat_bot;

import org.junit.Test;

public class StarterTest {
    private static final String FILE_WITH_ANSWERS = "src/main/resources/answers.txt";
    private static final String STRATEGY = "rand";

    @Test
    public void main() throws Exception {
        Starter.main(String.format("-r %s -f %s", STRATEGY, FILE_WITH_ANSWERS).split(" "));
    }

}