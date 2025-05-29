public class Main {
    public static void main(String[] args) {



        System.out.println("Employee Details: ");
        Employee emp1 = new Employee(101, "Alice", 60000, "IT");
        emp1.displayDetails();

        System.out.println();

        System.out.println("Manager Details: ");
        Employee mgr1 = new Manager(102, "Bob", 80000, "IT", 5);
        mgr1.displayDetails();

        System.out.println();

        System.out.println("HR Details: ");
        Employee hr1 = new HR(103, "Charlie", 70000, "Human Resources", 20);
        hr1.displayDetails();

        System.out.println();
        hr1.displayDetails(emp1);
    }
}