package demo.functional.inf;


// Interface can extend only other interfaces, not classes
@FunctionalInterface
interface ChildFunctionalInterface extends ParentFunctionalInterface {

    @Override
    void doSomething();
    // You can override the abstract method from the parent interface,
    // But you cannot add another abstract method

    // void doSomethingElse(); // This will give compilation error

    // You can add any number of default and static methods in the child interface too
    static void greetStatic() {
        System.out.println("Greeting static");
    }

    default void greetAgain() {
        System.out.println("Greeting again from child");
    }
}
