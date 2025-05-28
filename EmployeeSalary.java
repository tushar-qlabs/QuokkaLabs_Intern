import java.util.Scanner;

public class EmployeeSalary {

    public static void calculateEmployeeSalary(double baseSalary, double allowance) {

        double grossSalary = baseSalary + allowance;
        double taxRate = (grossSalary >= 50000) ? 0.10 : 0.0;
        double taxApplied = grossSalary * taxRate;
        double netSalary = grossSalary - taxApplied;

        System.out.println(
            "\nBase Salary: " + baseSalary +
            "\nAllowance: " + allowance +
            "\nGross Salary: " + grossSalary +
            "\nTax Applied: " + taxApplied +
            "\nNet Salary: " + netSalary
        );
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter Base Salary: ");
            double baseSalary = sc.nextDouble();

            System.out.print("Enter Allowance: ");
            double allowance = sc.nextDouble();

            EmployeeSalary.calculateEmployeeSalary(
                baseSalary,
                allowance
            );
        }
    }
}
