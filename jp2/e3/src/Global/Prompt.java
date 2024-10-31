package Global;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Exception.ValidationException;

public class Prompt {
    private static Scanner scanner;

    public static void init() {
        scanner = new Scanner(System.in);
    }
    public static void destroy() {
        scanner.close();
    }

    public static String string(String header) {
        return string(header, (x) -> null);
    }
    public static String string(String header, Function<String, String> validator) {
        String result;
        while (true) {
            try {
                System.out.print(
                    ANSI.format(ANSI.BOLD)
                    + header + ANSI.format(ANSI.CLEAR, ANSI.FG_LIGHT_GREEN, ANSI.ITALIC));
                result = scanner.nextLine().trim();
                System.out.print(ANSI.format(ANSI.CLEAR));
                String error = validator.apply(result);
                if (error != null) throw new ValidationException(error);
                return result;
            } catch (ValidationException e) {
                System.out.println(
                    ANSI.format(ANSI.FG_RED)
                    + e.getMessage() + ANSI.format(ANSI.CLEAR));
            }
        }
    }

    public static <T> T prompt(String header, Option<T>[] options) {
        String choice;
        Map<String, T> validInputs = Stream.of(options)
            .collect(Collectors.toMap(x -> x.getInput().toLowerCase(), Option<T>::getValue));
        while (true) {
            try {
                drawPrompt(header, options);
                System.out.print(
                    ANSI.format(ANSI.BOLD)
                    + "Your choice: " + ANSI.format(ANSI.CLEAR, ANSI.FG_LIGHT_GREEN, ANSI.ITALIC));
                choice = scanner.nextLine().trim().toLowerCase();
                System.out.print(ANSI.format(ANSI.CLEAR));
                if (!validInputs.containsKey(choice)) throw new ValidationException("Invalid choice");
                return validInputs.get(choice);
            } catch (ValidationException e) {
                System.out.println(
                    ANSI.format(ANSI.FG_RED)
                    + e.getMessage() + ANSI.format(ANSI.CLEAR));
            }
        }
    }

    private static void drawPrompt(String header, Option<?>[] options) {
        System.out.println(
            ANSI.format(ANSI.BOLD, ANSI.UNDERLINE)
            + header + ANSI.format(ANSI.CLEAR));
        for (Option<?> option : options) {
            System.out.println(
                ANSI.format(ANSI.FG_LIGHT_YELLOW) + option.input + ")"
                + ANSI.format(ANSI.CLEAR) + " " + option.label);
        }
    }

    public static class Option<T> {
        private T value;
        private String input;
        private String label;

        public static <T> Option<T>[] list (Option<T>... list) {
            return list;
        }

        public static Option<String> of (String input, String label) {
            return new Option<String>(input, input, label);
        }
        
        public Option(String input, T value, String label) {
            this.value = value;
            this.input = input;
            this.label = label;
        }

        public T getValue() {
            return value;
        }
        public String getInput() {
            return input;
        }
        public String getLabel() {
            return label;
        }
    }
}
