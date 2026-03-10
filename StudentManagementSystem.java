import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadStudents();   // load saved students

        while(true){

            System.out.println("\n1 Add Student");
            System.out.println("2 View Students");
            System.out.println("3 Delete Student");
            System.out.println("4 Update Student");
            System.out.println("5 Search Student");
            System.out.println("6 Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    deleteStudent(sc);
                    break;

                case 4:
                    updateStudent(sc);
                    break;

                case 5:
                    searchStudent(sc);
                    break;

                case 6:
                    saveStudents();
                    System.out.println("Data saved. Exiting...");
                    System.exit(0);
            }
        }
    }

    static void addStudent(Scanner sc){

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        students.add(new Student(id,name,course,marks));

        System.out.println("Student Added Successfully!");
    }

    static void viewStudents(){

        if(students.isEmpty()){
            System.out.println("No students found.");
            return;
        }

        for(Student s : students){
            s.display();
        }
    }

    static void deleteStudent(Scanner sc){

        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        students.removeIf(s -> s.id == id);

        System.out.println("Student Deleted!");
    }

    static void updateStudent(Scanner sc){

        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for(Student s : students){

            if(s.id == id){

                System.out.print("Enter new Name: ");
                s.name = sc.nextLine();

                System.out.print("Enter new Course: ");
                s.course = sc.nextLine();

                System.out.print("Enter new Marks: ");
                s.marks = sc.nextInt();

                System.out.println("Student Updated Successfully!");
                return;
            }
        }

        System.out.println("Student not found!");
    }

    static void searchStudent(Scanner sc){

        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();

        for(Student s : students){

            if(s.id == id){
                s.display();
                return;
            }
        }

        System.out.println("Student not found!");
    }

    static void saveStudents(){

        try{
            PrintWriter writer = new PrintWriter("students.txt");

            for(Student s : students){
                writer.println(s.id + "," + s.name + "," + s.course + "," + s.marks);
            }

            writer.close();

        }catch(Exception e){
            System.out.println("Error saving file");
        }
    }

    static void loadStudents(){

        try{
            File file = new File("students.txt");

            if(!file.exists()) return;

            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine()){

                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                students.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        Integer.parseInt(data[3])
                ));
            }

            fileScanner.close();

        }catch(Exception e){
            System.out.println("Error loading file");
        }
    }
}