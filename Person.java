public abstract class Person {

    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public abstract void showRole();

    public void greet() {
        System.out.println("Hello. my name is " + name);
    }
}
