public class HR extends Employee {

    public int recruitmentsHandled;
    public HR(int id, String name, double salary, String department, int requirementsHandled) {
        super(id, name, salary, department);
        this.recruitmentsHandled = requirementsHandled;
    }

    @Override
    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + getSalary());
        System.out.println("Department: " + department);
        System.out.println("Recruitments Handled: " + recruitmentsHandled);
    }
}
