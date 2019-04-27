package chat_bot;

import chat_bot.responses.RandomResponseGenerator;
import chat_bot.responses.ResponseGenerator;

import java.util.List;
import java.util.MissingFormatArgumentException;

public class Starter {

    private static final String BOTS_NAME = "[Бот] ";
    private static final String MY_NAME = "[Я] ";
    private static final String FILE_NAME_MARKER = "-f";
    private static final String STRATEGY_MARKER = "-r";

    private static Strategy currentStrategy;
    private static ResponseGenerator random;
    private static ResponseGenerator up;
    private static ResponseGenerator down;

    // > ~> talk_to_me -r rand -f ~/answers.txt
    public static void main(String[] args) {
        String fileName = getParameterFromArray(args, FILE_NAME_MARKER);
        Strategy initialStrategy = Strategy.fromString(getParameterFromArray(args, STRATEGY_MARKER));

        System.out.println("initial strategy = " + initialStrategy);
        List<String> strings = FileLoader.readFileInList(fileName);
        random = new RandomResponseGenerator(strings);
        currentStrategy = Strategy.RANDOM;
        ResponseGenerator generator;
        switch (currentStrategy) {
            case RANDOM:
            case UP_SEQUENCE:
            case DOWN_SEQUENCE:
            default:
                generator = random;
                break;
        }
        System.out.println(BOTS_NAME + generator.getResponse());
    }

    private static String getParameterFromArray(String[] args, String parameterName) {
        String result = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(parameterName)) {
                if (i < args.length - 1) {
                    result = args[i + 1];
                } else {
                    throw new MissingFormatArgumentException(String.format("after %s should be a parameter.", parameterName));
                }
            }
        }
        return result;
    }

    private enum Strategy {
        RANDOM("rand"),
        UP_SEQUENCE("upseq"),
        DOWN_SEQUENCE("downseq");

        private final String text;

        private static Strategy fromString(String text) {
            for (Strategy strategy : Strategy.values()) {
                if (strategy.text.equalsIgnoreCase(text)) {
                    return strategy;
                }
            }
            return null;
        }

        Strategy(String text) {
            this.text = text;
        }

    }


}
