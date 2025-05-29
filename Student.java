public class Student extends Person implements Graded {

    private int marks;
    private static int studentCount = 0;

    public Student(String name, int marks) {
        super(name);
        this.marks = marks;
        studentCount++;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public void showRole() {
        System.out.println("I am a student.");
    }

    @Override
    public void greet() {
        super.greet();
    }

    @Override
    public void displayGrades() {
        if (marks >= 90) {
            System.out.println("Grade: A");
        } else if (marks >= 75) {
            System.out.println("Grade: B");
        } else {
            System.out.println("Grade: C");
        }
    }

    public static int getStudentCount() {
        return studentCount;
    }
}
