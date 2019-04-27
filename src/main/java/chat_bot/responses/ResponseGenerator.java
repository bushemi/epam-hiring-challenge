package chat_bot.responses;

import java.util.List;

abstract class ResponseGenerator {
    List<String> responses;

    ResponseGenerator(List<String> responses) {
        this.responses = responses;
    }

    abstract String getResponse();
}
