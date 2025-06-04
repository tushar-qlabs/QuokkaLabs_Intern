package demo.functional.inf;

// @FunctionalInterface annotation is used to ensure that the interface has only one abstract method.
// If we try to add another abstract method, it will throw a compilation error.

// This annotation is not mandatory, but it is good practice to use it to avoid any accidental addition of abstract methods.
// It is also used to make it clear that the interface is meant to be used as a functional interface.

// A functional interface can have default and static methods.
// A functional interface can have only one abstract method, but it can have multiple default and static methods.

@FunctionalInterface
public interface ParentFunctionalInterface {
    void doSomething();

    // You can add any number of default and static methods
    default void doSomethingElse() {
        System.out.println("Doing something else");
    }

    static void doSomethingStatic() {
        System.out.println("Doing something static");
    }
}



