package chat_bot.responses;

import java.util.List;

public abstract class ResponseGenerator {
    List<String> responses;

    ResponseGenerator(List<String> responses) {
        this.responses = responses;
    }

    public abstract String getResponse();
}
