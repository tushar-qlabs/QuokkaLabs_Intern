public class Employee {

    public int id;
    public String name;
    private final double salary;
    public String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }
    
    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + getSalary());
        System.out.println("Department: " + department);
    }
}
