import java.io.*;
import java.util.*;

class Student {
    String name, rollNumber;
    int marks;

    public Student(String name, String rollNumber, int marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    public String toString() {
        return name + "," + rollNumber + "," + marks;
    }
}

public class StudentManagement {
    private static final String FILE_PATH = "students.csv";
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudents();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. View All Students\n2. Search Student\n3. Add Student\n4. Update Student\n5. Delete Student\n6. Exit");
            switch (scanner.nextInt()) {
                case 1 -> students.forEach(System.out::println);
                case 2 -> searchStudent(scanner);
                case 3 -> addStudent(scanner);
                case 4 -> updateStudent(scanner);
                case 5 -> deleteStudent(scanner);
                case 6 -> { saveStudents(); return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                students.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2])));
            }
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }

    private static void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) writer.write(student + "\n");
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter roll number: ");
        students.stream()
                .filter(s -> s.rollNumber.equals(scanner.next()))
                .forEach(System.out::println);
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter name, roll number, marks: ");
        students.add(new Student(scanner.next(), scanner.next(), scanner.nextInt()));
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter roll number: ");
        String roll = scanner.next();
        students.stream().filter(s -> s.rollNumber.equals(roll)).findFirst().ifPresentOrElse(s -> {
            System.out.print("Enter new name and marks: ");
            s.name = scanner.next();
            s.marks = scanner.nextInt();
        }, () -> System.out.println("Student not found"));
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter roll number: ");
        students.removeIf(s -> s.rollNumber.equals(scanner.next()));
    }
}
