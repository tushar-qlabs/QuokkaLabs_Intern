public class Manager extends Employee {

    public int teamSize;
    public Manager(int id, String name, double salary, String department, int teamSize) {
        super(id, name, salary, department);
        this.teamSize = teamSize;
    }

    @Override
    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + getSalary());
        System.out.println("Department: " + department);
        System.out.println("Team Size: " + teamSize);
    }

    public void printEmployeeDetails(Employee emp) {
        System.out.println("ID: " + emp.id);
        System.out.println("Name: " + emp.name);
        System.out.println("Salary: " + emp.getSalary());
        System.out.println("Department: " + emp.department);
    }
}
