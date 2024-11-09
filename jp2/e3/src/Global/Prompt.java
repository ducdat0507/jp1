package Global;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import Entity.Customer;
import Exception.ValidationException;
import IGeneral.IGenericController;

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

    public static <T> T search(String header, IGenericController<T> controller, Function<String, Predicate<T>> criteria) {
        List<T> activeItems = controller.stream().limit(10).toList();
        String choice;
        while (true) {
            try {
                System.out.println(
                    ANSI.format(ANSI.CLEAR, ANSI.UNDERLINE, ANSI.BOLD)
                    + header + ANSI.format(ANSI.CLEAR));

                AtomicInteger i = new AtomicInteger();
                activeItems.forEach(x -> {
                    System.out.println(
                        ANSI.format(ANSI.FG_LIGHT_YELLOW) + ":" + i.incrementAndGet() + ")"
                        + ANSI.format(ANSI.CLEAR) + " " + x);
                });

                System.out.print(
                    ANSI.format(ANSI.CLEAR, ANSI.BOLD)
                    + "Select with ':num' or search: " + ANSI.format(ANSI.CLEAR, ANSI.FG_LIGHT_GREEN, ANSI.ITALIC));
                choice = scanner.nextLine().trim();
                System.out.print(ANSI.format(ANSI.CLEAR));


                if (choice.startsWith(":")) {
                    int choiceIdx;
                    try { choiceIdx = Integer.parseInt(choice.substring(1)); }
                    catch (NumberFormatException e) { throw new ValidationException("Choice must be a number"); }

                    if (choiceIdx < 0 || choiceIdx >= activeItems.size()) throw new ValidationException("Choice out of bounds");

                    return activeItems.get(choiceIdx);
                } else if (!choice.isEmpty()) {
                    Predicate<T> tester = criteria.apply(choice);
                    List<T> newActiveItems = controller.stream().filter(tester).limit(10).toList();
                    if (newActiveItems.size() == 0) throw new ValidationException("No items found");
                    else if (newActiveItems.size() == 1) return activeItems.get(0);
                    else if (newActiveItems.size() >= 1) activeItems = newActiveItems;
                }
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
            return new Option<>(input, input, label);
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
