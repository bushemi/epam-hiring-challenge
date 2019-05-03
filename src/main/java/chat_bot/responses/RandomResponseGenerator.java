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
        int size = responses.size();
        if (size < 1) return "";
        int index = random.nextInt(size);
        return responses.get(index);
    }
}
