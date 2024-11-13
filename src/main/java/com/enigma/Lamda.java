package com.enigma;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.function.*;
import java.util.stream.Collectors;

public class Lamda {
    public static void main(String[] args) {
        // 2. Basic Lambda Syntax
        Calculator add = (x, y) -> x + y;
        Calculator subtract = (x, y) -> x - y;
        Calculator multiply = (x, y) -> x * y;

        Runnable runnable = () -> {
            System.out.println("woy");
        };
        runnable.run();
        System.out.println("Addition: " + add.operate(5, 3));
        System.out.println("Subtraction: " + subtract.operate(5, 3));

        // 3. Method References
        String str = "Hello";
        Converter<String, Integer> lengthFinder = String::length;
        System.out.println("Length: " + lengthFinder.convert(str));

        // Static method reference
        Converter<String, Integer> parser = Integer::parseInt;
        System.out.println("Parsed: " + parser.convert("123"));

        // Constructor reference
        Converter<String, StringBuilder> constructor = StringBuilder::new;

        // 4. Variable Capture
        int multiplier = 10;
        Calculator multipliedAdd = (x, y) -> (x + y) * multiplier;

        // 5. Common Functional Interfaces
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println("Is empty: " + isEmpty.test(""));

        Consumer<String> printer = System.out::println;
        printer.accept("Hello Lambda!");

        Function<String, Integer> strLength = String::length;
        System.out.println("String length: " + strLength.apply("Hello"));

        Supplier<Double> random = Math::random;
        System.out.println("Random: " + random.get());

        BiFunction<Integer, Integer, Integer> sum = Integer::sum;
        System.out.println("Sum: " + sum.apply(5, 3));

        // 6. Composition of Functions
        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> addBy3 = x -> x + 3;

        Function<Integer, Integer> addThenMultiply = multiplyBy2.compose(addBy3);
        Function<Integer, Integer> multiplyThenAdd = multiplyBy2.andThen(addBy3);

        System.out.println("Add then multiply: " + addThenMultiply.apply(5));
        System.out.println("Multiply then add: " + multiplyThenAdd.apply(5));

        // 7. Exception Handling in Lambdas
        Function<String, Integer> safeParser = s -> {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return -1;
            }
        };

        // 8. Using Lambdas with Collections
        List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");

        Collections.sort(names, (a, b) -> a.compareToIgnoreCase(b));

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("J"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // 9. Parallel Processing with Lambdas
        names.parallelStream()
                .filter(name -> name.length() > 3)
                .forEach(name -> System.out.println("Processing: " + name));
    }
}