/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author hazem
 */
public class Course {

    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private ArrayList<Lesson> lessons;
    private ArrayList<String> studentIds;

    public Course(String courseId, String title, String description, String instructorId, ArrayList<Lesson> lessons, ArrayList<String> studentIds) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = lessons;
        this.studentIds = studentIds;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public ArrayList<String> getStudentIds() {
        return studentIds;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void enrollStudent(String studentId) {
        if (!studentIds.contains(studentId)) {
            studentIds.add(studentId);
        }
    }

}
