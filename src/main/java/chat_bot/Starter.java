package chat_bot;

import chat_bot.responses.RandomResponseGenerator;
import chat_bot.responses.ResponseGenerator;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Starter {

    private static final String BOTS_NAME = "[Бот] ";
    private static final String MY_NAME = "[Я] ";
    private static final String FILE_NAME_MARKER = "-f";
    private static final String STRATEGY_MARKER = "-r";
    private static final String START_OF_LINE = "> ";
    private static final String END_OF_CONVERSATION = "пока";

    private static ResponseGenerator random;
    private static ResponseGenerator up;
    private static ResponseGenerator down;

    // > ~> talk_to_me -r rand -f ~/answers.txt
    public static void main(String[] args) {
        String fileName = getParameterFromArray(args, FILE_NAME_MARKER);
        Strategy initialStrategy = Strategy.fromString(getParameterFromArray(args, STRATEGY_MARKER));

        List<String> strings = FileLoader.readFileInList(fileName);
        random = new RandomResponseGenerator(strings);
        ResponseGenerator generator;

        generator = getResponseGeneratorForStrategy(initialStrategy);

        talk(generator);
    }

    private static ResponseGenerator getResponseGeneratorForStrategy(Strategy initialStrategy) {
        ResponseGenerator generator;
        switch (initialStrategy) {
            case RANDOM:
            case UP_SEQUENCE:
            case DOWN_SEQUENCE:
            default:
                generator = random;
                break;
        }
        return generator;
    }

    private static void talk(ResponseGenerator generator) {
        Strategy currentStrategy;

        showBotsMessage("Hello");
        System.out.println();
        showStartOfClientsLine();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String newLine = scanner.nextLine();
            if (newLine.equalsIgnoreCase(END_OF_CONVERSATION)) {
                showBotsMessage(END_OF_CONVERSATION);
                break;
            }
            String response = generator.getResponse();

            int indexOf = newLine.toLowerCase().indexOf("стратегия:".toLowerCase());
            if (indexOf >= 0) {

                int beginIndex = indexOf + 11;
                if (beginIndex <= newLine.length()) {
                    String newStrategy = newLine.substring(beginIndex);
                    response += " Использую стратегию: " + newStrategy;
                    currentStrategy = Strategy.fromString(newStrategy);
                    if (isNull(currentStrategy)) {
                        response = "У тебя в голове мозги или кю?!";
                    } else {
                        generator = getResponseGeneratorForStrategy(currentStrategy);
                    }
                }
            }
            showBotsMessage(response);
            System.out.println();
            showStartOfClientsLine();
        }
    }

    private static void showStartOfClientsLine() {
        System.out.print(START_OF_LINE + MY_NAME);
    }

    private static void showBotsMessage(String message) {
        System.out.print(START_OF_LINE + BOTS_NAME + message);
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
