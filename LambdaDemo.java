package demo.functional.inf;

/*
 Functional interfaces are interfaces that have exactly one abstract method.
 They can have multiple default methods or static methods.
 They are also known as Single Abstract Method (SAM) interfaces.
 Functional interfaces can be implemented using lambda expressions.
 Java 8 has defined a lot of builtin functional interfaces in the java.util.function package.
 Some of the commonly used functional interfaces are:

 Predicate<T>: Takes one argument and returns a boolean
 Function<T, R>: Takes one argument and returns a result
 Consumer<T>: Takes one argument and returns no result
 Supplier<T>: Takes no arguments and returns a result
 UnaryOperator<T>: Takes one argument and returns a result of the same type
 BinaryOperator<T>: Takes two arguments of the same type and returns a result of the same type
 BiFunction<T, U, R>: Takes two arguments and returns a result
 BiConsumer<T, U>: Takes two arguments and returns no result
 BiPredicate<T, U>: Takes two arguments and returns a boolean
 Runnable: Takes no arguments and returns no result
*/

import java.util.function.*;

public class LambdaDemo {

    public static void main(String[] args) {

        // Runnable: Takes no arguments and returns no result
        Runnable task = () -> System.out.println("Task is running");
        new Thread(task).start(); // Task is running

        // Predicate<T>: Takes one argument and returns a boolean
        Predicate<Integer> isEven = (n) -> n % 2 == 0;
        Predicate<Integer> isOdd = isEven.negate();
        Predicate<Integer> isDivisibleByThree = (n) -> n % 3 == 0;
        Predicate<Integer> isEvenAndIsDivisibleByThree = isEven.and(isDivisibleByThree);

        Predicate<String> isEquals = Predicate.isEqual(null);
        // Using Predicate.isEqual(null) directly conveys that you want to check if an element is equal to a specific value
        // Predicate.isEqual(null) handles null values safely. In contrast, using s.equals(null) directly might lead to a NullPointerException if s is null, because equals methods often assume that the object they're comparing is non-null.
        Predicate<String> isSame = (s) -> s.equals(null);

        System.out.println("Is 3 even and odd? " + isEven.and(isDivisibleByThree).test(6));
        System.out.println("Is 3 even and odd? " + isEvenAndIsDivisibleByThree.test(8)); // false
        System.out.println("Is 4 even? " + isEven.test(4)); // true
        System.out.println("Is 5 odd? " + isOdd.test(5)); // true
        System.out.println("Is 6 odd or divisible by 3? " + isOdd.or(isDivisibleByThree).test(6)); // true
        System.out.println("Is `Hello` equal to null? " + isEquals.test("Hello")); // true

        System.out.println("\n-----------------------------------\n");

        // Function<T, R>: Takes one argument and returns a result
        Function<String, String> toUpperCase = (s) -> s.toUpperCase();
        Function<String, String> stringLength = (s) -> s.substring(0, 3);
        Function<String, String> identity = Function.identity(); // Returns the input as it is

        System.out.println("Uppercase of 'hello': " + toUpperCase.apply("hello")); // HELLO
        System.out.println("Length of 'hello': " + stringLength.apply("hello")); // hel
        System.out.println("Combined result: " + toUpperCase.andThen(stringLength).apply("hello")); // HEL (First toUpperCase and then stringLength)
        System.out.println("Combined result (Output will still be same in this case): " + toUpperCase.compose(stringLength).apply("hello")); // Hel (First stringLength and then toUpperCase)
        System.out.println("Identity of 'hello': " + identity.apply("hello")); // hello

        System.out.println("\n-----------------------------------\n");

        // Consumer<T>: Takes one argument and returns no result
        Consumer<String> print = (s) -> System.out.println(s);
        print.accept("Hello, World!"); // Hello, World!

        // Supplier<T>: Takes no arguments and returns a result
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value: " + randomValue.get());


        // UnaryOperator<T>: Takes one argument and returns a result of the same type
        UnaryOperator<Integer> square = (n) -> n * n;
        System.out.println("Square of 5: " + square.apply(5)); // 25

        // BinaryOperator<T>: Takes two arguments of the same type and returns a result of the same type
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println("Sum of 3 and 5: " + sum.apply(3, 5)); // 8

        // BiFunction<T, U, R>: Takes two arguments and returns a result
        BiFunction<String, String, String> concat = (a, b) -> a + b;
        System.out.println("Concatenation of 'Hello' and 'World': " + concat.apply("Hello", "World")); // HelloWorld

        // BiConsumer<T, U>: Takes two arguments and returns no result
        BiConsumer<String, Integer> printKeyValue = (key, value) -> System.out.println(key + ": " + value);
        printKeyValue.accept("Age", 30); // Age: 30

        // BiPredicate<T, U>: Takes two arguments and returns a boolean
        BiPredicate<String, Integer> isLongerThan = (str, len) -> str.length() > len;
        System.out.println("Is 'hello' longer than 3? " + isLongerThan.test("hello", 3)); // true

    }
}
