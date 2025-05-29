public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rohit", 88);
        Student s2 = new Student("Sam", 94);

        s1.greet();
        s1.showRole();
        s1.displayGrades();

        System.out.println();

        s2.greet();
        s2.showRole();
        s2.displayGrades();

        System.out.println();

        System.out.println("Total students: " + Student.getStudentCount());
    }
}
