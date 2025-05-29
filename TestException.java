class MyException extends Exception {
    MyException(String msg) { super(msg); }
}

public class TestException {
    static void risky() throws MyException {
        throw new MyException("Error happened");
    }

    public static void main(String[] args) {
        try {
            risky();
        } catch (MyException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block runs always");
        }
    }
}
