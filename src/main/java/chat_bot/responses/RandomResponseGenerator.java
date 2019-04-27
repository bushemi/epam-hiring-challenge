package chat_bot.responses;

import java.util.List;
import java.util.Random;

public class RandomResponseGenerator extends ResponseGenerator {
    private Random random = new Random();

    public RandomResponseGenerator(List<String> responses) {
        super(responses);
    }

    @Override
    public String getResponse() {
        return responses.get(random.nextInt(responses.size()));
    }
}
