package chat_bot.responses;

import org.junit.Test;

import java.util.Arrays;

public class RandomResponseGeneratorTest {
    RandomResponseGenerator generator = new RandomResponseGenerator(Arrays.asList("123", "test", "abc"));

    @Test
    public void getResponse() throws Exception {
        String response = generator.getResponse();
        System.out.println("response = " + response);
    }

}