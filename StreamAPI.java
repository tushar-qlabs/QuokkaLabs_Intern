import java.util.*;
import java.util.stream.*;

record Employee(int id, String name, String dept, int salary) {}
record NameDept(String name, String dept) {}

public class StreamAPI {
	public static void main(String[] args) {
	    
    List<Employee> empList = new ArrayList<>(List.of(
        new Employee(101, "Josh", "IT", 60000),
        new Employee(102, "Bhola", "HR", 50000),
        new Employee(103, "Raj", "Finance", 60000),
        new Employee(104, "Aditya", "IT", 75000),
        new Employee(105, "Rohit", "Marketing", 55000),
        new Employee(106, "Tushar", "IT", 72000),
        new Employee(107, "Sneha", "HR", 48000),
        new Employee(108, "Priya", "Finance", 62000),
        new Employee(109, "Aman", "IT", 68000),
        new Employee(110, "Neha", "Marketing", 53000)
    ));
	    
    // Increment IT employees' salary by 15%, sort by salary descending, then partition by salary >= 70000
	Map<Boolean, List<Employee>> empITList = empList.stream()
	    .filter(e -> e.dept().equals("IT"))
        .map(e -> new Employee(e.id(), e.name(), e.dept(), (int) (e.salary() * 1.15))) // 15% increment for IT dept.
        .sorted(Comparator.comparing(Employee::salary).reversed())
        .collect(Collectors.partitioningBy(e -> e.salary() >= 70000));

        
    empITList.entrySet().forEach(System.out::println);
    System.out.println("\n- - - - - - - - - - - - - - - - - - - - - -\n");
    
    // Group by dept and only keep Name and Dept in result.
    Map<String, List<NameDept>> groupByDept = empList.stream()
        .collect(Collectors.groupingBy(Employee::dept, Collectors.mapping(e -> new NameDept(e.name(), e.dept()), Collectors.toList())));
    
    groupByDept.entrySet()
        .forEach(e -> {
            System.out.println(e.getKey());
            e.getValue().forEach(u -> {
                System.out.println("  -" + u);
            });
        });
	}
}
