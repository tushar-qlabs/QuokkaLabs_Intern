import java.util.*;

class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private String dept;
    private int salary;

    public Employee(int id, String name, String dept, int salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', dept='" + dept + "', salary=" + salary + "}";
    }
}

class EmployeeFieldIterator implements Iterable<Object> {
    private final Employee employee;

    public EmployeeFieldIterator(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Iterator<Object> iterator() {
        return Arrays.asList(
            (Object) employee.getId(),
            employee.getName(),
            employee.getDept(),
            employee.getSalary()
        ).iterator();
    }
}

public class JavaCollection {
	public static void main(String[] args) {
	    
	    List<Employee> empArrayList = new ArrayList<>(List.of(
	        new Employee(101, "Cloe", "DEV", 50000),
	        new Employee(102, "Jack", "SALES", 60000),
	        new Employee(103, "Fern", "DEV", 450000)
	    ));
	    
	    empArrayList.add(new Employee(104, "Bob", "IT", 45000));
	    empArrayList.set(1, new Employee(105, "Joe", "TESTER", 350000));
	    empArrayList.remove(0);
	    empArrayList.forEach(System.out::println);
	    
	    Deque<Employee> empLinkedList = new LinkedList<>(List.of(
	    	new Employee(106, "Danny", "DEV", 60000),
	        new Employee(107, "Raz", "SALES", 50000),
	        new Employee(108, "Argon", "IT", 450000)
	   ));
	   
	   System.out.println(empLinkedList.pollFirst());
	   System.out.println(empLinkedList.pollLast());
	   
	   empLinkedList.forEach(System.out::println);
	   
	   Set<Integer> simpleSet = new HashSet<>(List.of(105, 102, 103, 104, 102)); // pretty simple set, fast lookup
	   Set<Integer> treeSet = new TreeSet<>(List.of(106, 105, 107, 106, 108)); // maintain the sorting order
	   Set<Integer> linkedHashSet = new LinkedHashSet<>(List.of(111, 112, 111, 113, 114)); // maintain the insertion order
	   
	   simpleSet.add(106);
	   treeSet.remove(107);
	   
	   System.out.println(linkedHashSet.contains(115));
	   
	   System.out.println("HashSet: " + simpleSet);
	   System.out.println("TreeSet: " + treeSet);
	   System.out.println("LinkedHasSet: " + linkedHashSet);
	   
	    Map<Integer, String> hashMap = new HashMap<>(Map.of(
            1, "One",
            2, "Two",
            3, "Three"
        ));

        Map<Integer, String> treeMap = new TreeMap<>(Map.of(
            5, "Five",
            4, "Four",
            6, "Six"
        ));
        
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(10, "Ten");
        linkedHashMap.put(11, "Eleven");
        linkedHashMap.put(10, "Tennnnnn!");
        linkedHashMap.put(12, "Twelve");

        System.out.println("HashMap: " + hashMap);
        System.out.println("TreeMap: " + treeMap);
        System.out.println("LinkedHashMap: " + linkedHashMap);
        
        System.out.print("Employee properties: ");
        for(Object prop: new EmployeeFieldIterator(empArrayList.get(1))) {
            System.out.print(prop + ", ");
        }
        
        empArrayList.stream().sorted(
            Comparator.comparing(Employee::getId).reversed()
        ).forEach(System.out::println);
	}
}
