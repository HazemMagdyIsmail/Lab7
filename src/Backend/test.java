/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author patrick
 */
public class test {
    public static void main(String[] args) {
        // Load existing courses from JSON
        CourseJsonDatabase.loadCourses();

        // Create a new lesson
        ArrayList<String> resources1 = new ArrayList<>();
        resources1.add("https://example.com/variables");
        Lesson lesson1 = new Lesson("L1", "Lesson 1: Variables", "Intro to variables in Java", resources1);

        ArrayList<String> resources2 = new ArrayList<>();
        resources2.add("https://example.com/loops");
        Lesson lesson2 = new Lesson("L2", "Lesson 2: Loops", "Intro to loops in Java", resources2);

        // Create a new course
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson1);
        lessons.add(lesson2);

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("S1");
        studentIds.add("S2");

        Course newCourse = new Course("C1", "Java Programming", "Intro to Java basics", "I1", lessons, studentIds);

        // Add course to database
        boolean added = CourseJsonDatabase.addCourse(newCourse);
        if (added) {
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Course already exists.");
        }

        // Display all courses and their lessons
        for (Course course : CourseJsonDatabase.getAllCourses()) {
            System.out.println("Course: " + course.getTitle());
            for (Lesson lesson : course.getLessons()) {
                System.out.println("  - " + lesson.getTitle() + ": " + lesson.getContent());
            }
        }
    }

    
}
