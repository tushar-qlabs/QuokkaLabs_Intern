public class Main {

    public static void printEmployeeDetails(Employee emp) {
        emp.displayDetails();
    }

    public static void main(String[] args) {

        System.out.println("\nEmployee Details: ");
        printEmployeeDetails(
                new Employee(101, "Alice", 50000, "Sales")
        );

        System.out.println("\nManager Details: ");
        printEmployeeDetails(
                new Manager(102, "Bob", 80000, "IT", 5)
        );

        System.out.println("\nHR Details: ");
        printEmployeeDetails(
                new HR(103, "Charlie", 70000, "Human Resources", 20)
        );
    }
}
