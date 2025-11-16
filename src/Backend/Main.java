/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.IOException;

/**
 *
 * @author hazem
 */
public class Main {
    public static void main(String[] args) throws IOException {

        UserJsonDatabase db = new UserJsonDatabase();

        try {
            // --- Read from file ---
            db.readFromFile();

            // --- Print all students ---
            System.out.println("=== Students ===");
            for (Student s : db.getStudents()) {
                System.out.println("UserId: " + s.getUserId());
                System.out.println("Username: " + s.getUsername());
                System.out.println("Email: " + s.getEmail());
                System.out.println("Enrolled Courses: " + s.getEnrolledCourses());
                System.out.println("Progress: " + s.getProgress());
                System.out.println("----------------------");
            }

            // --- Print all instructors ---
            System.out.println("=== Instructors ===");
            for (Instructor ins : db.getInstructors()) {
                System.out.println("UserId: " + ins.getUserId());
                System.out.println("Username: " + ins.getUsername());
                System.out.println("Email: " + ins.getEmail());
                System.out.println("Created Courses: " + ins.getCreatedCourses());
                System.out.println("----------------------");
            }

            // --- Optional: Save back to file (pretty JSON) ---
            db.saveToFile();
            System.out.println("Data saved back to file successfully.");

        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }

}
