package Backend;

import java.util.ArrayList;

public class BackendTester {
    public static void main(String[] args) {
        System.out.println("🔍 Loading courses...");
        CourseJsonDatabase.loadCourses();

        System.out.println("📚 Initial course count: " + CourseJsonDatabase.getAllCourses().size());

        // Create lessons
        ArrayList<String> res1 = new ArrayList<>();
        res1.add("https://example.com/intro");
        Lesson l1 = new Lesson("L101", "Intro to Java", "Basics of Java", res1);

        ArrayList<String> res2 = new ArrayList<>();
        res2.add("https://example.com/oop");
        Lesson l2 = new Lesson("L102", "OOP Concepts", "Understanding OOP", res2);

        // Create course
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(l1);
        lessons.add(l2);

        ArrayList<String> students = new ArrayList<>();
        students.add("S1001");
        students.add("S1002");

        Course course = new Course("C101", "Java Fundamentals", "Learn Java from scratch", "I100", lessons, students);

        // Add course
        boolean added = CourseJsonDatabase.addCourse(course);
        System.out.println(added ? "✅ Course added." : "⚠️ Course already exists.");

        // Retrieve course
        Course retrieved = CourseJsonDatabase.getCourseById("C101");
        if (retrieved != null) {
            System.out.println("📦 Retrieved course: " + retrieved.getTitle());
            System.out.println("👨‍🏫 Instructor: " + retrieved.getInstructorId());
            System.out.println("👥 Students: " + retrieved.getStudentIds());
            System.out.println("📘 Lessons:");
            for (Lesson lesson : retrieved.getLessons()) {
                System.out.println("  - " + lesson.getTitle() + ": " + lesson.getContent());
            }

            // Update a lesson
            ArrayList<String> newRes = new ArrayList<>();
            newRes.add("https://example.com/updated");
            retrieved.updateLesson("L101", "Intro to Java (Updated)", "Updated content", newRes);

            // Remove a lesson
            retrieved.removeLesson("L102");
            System.out.println("✏️ Updated lessons:");
            for (Lesson lesson : retrieved.getLessons()) {
                System.out.println("  - " + lesson.getTitle());
            }
        } else {
            System.out.println("❌ Course not found.");
        }

        // Save changes
        CourseJsonDatabase.saveCourses();
        System.out.println("💾 Changes saved.");
    }
}