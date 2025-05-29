import java.util.Arrays;
import java.util.Scanner;

public class StudentGrade {

    public static int[] getMarks(String marksString) {
        return Arrays.stream(marksString.trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int getTotalMarks(int[] marks) {
        return Arrays.stream(marks).sum();
    }

    public static int getAverageMarks(int[] marks) {
        return getTotalMarks(marks) / 3;
    }

    private static int getMaxMarks(int[] marks, int index) {
        if (index == marks.length - 1) {
            return marks[index];
        }
        return Math.max(marks[index], getMaxMarks(marks, index + 1));
    }

    public static String getGrade(int averageMarks) {
        if (averageMarks >= 90) return "A";
        else if (averageMarks >= 80) return "B";
        else if (averageMarks >= 70) return "C";
        else if (averageMarks >= 60) return "D";
        else return "F";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = Integer.parseInt(sc.nextLine());

        int highestMarks = 0;

        for (int i = 0; i < numStudents; i++) {
            System.out.print("\nEnter name of student " + (i + 1) + ": ");
            String studentName = sc.nextLine();

            System.out.print("Enter marks in 3 subjects (space-separated): ");
            int[] marks = getMarks(sc.nextLine());

            if (marks.length != 3) {
                System.out.println("Invalid input for " + studentName + ". Please enter exactly 3 marks.");
                continue;
            }

            int total = getTotalMarks(marks);
            int average = getAverageMarks(marks);
            String grade = getGrade(average);

            int maxMarks = getMaxMarks(marks, 0);
            if (maxMarks > highestMarks) {
                highestMarks = maxMarks;
            }

            System.out.println("Student: " + studentName);
            System.out.println("Total Marks: " + total);
            System.out.println("Average Marks: " + average);
            System.out.println("Grade: " + grade);
        }

        System.out.println("\nHighest marks among all students: " + highestMarks);
    }
}
