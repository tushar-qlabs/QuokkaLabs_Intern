import java.util.*;

record Employee(int id, String name, String dept, int salary) {}

public class StringAndArrays {
    public static void main(String[] args) {
        
        Employee[] empArray =  {
            new Employee(106, "Cloe", "DEV", 50000),
            new Employee(112, "Jack", "SALES", 60000),
            new Employee(134, "Fern", "DEV", 450000),
            new Employee(167, "Josh", "IT", 55000)
        };
        
        Object[][] empArrayWithRow = new Object[2][empArray.length];
        
        for(int i = 0; i < empArray.length; i++) {
            empArrayWithRow[0][i] = i;           
            empArrayWithRow[1][i] = empArray[i];
        }
        
        // Some common string methods
        for (int i = 0; i < empArray.length; i++) {
            Employee emp = (Employee) empArrayWithRow[1][i];
            String name = emp.name();
            String dept = emp.dept();

            System.out.println("Original name: " + name);
            System.out.println("Uppercase: " + name.toUpperCase());
            System.out.println("Lowercase: " + name.toLowerCase());
            System.out.println("Starts with J? " + name.startsWith("J"));
            System.out.println("Ends with h? " + name.endsWith("h"));
            System.out.println("Length: " + name.length());
            System.out.println("Char at index 1: " + name.charAt(1));
            System.out.println("Reversed: " + new StringBuilder(name).reverse());
            System.out.println("Dept padded: " + String.format("%-10s", dept));
        }
    }
}
