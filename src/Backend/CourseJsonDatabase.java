/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author hazem
 */
public class CourseJsonDatabase {

    private static final String FILE_NAME = "Courses.JSON";
    private static ArrayList<Course> courseList = new ArrayList<>();
   
    public static ArrayList<Course> getAllCourses() {
        return courseList;
    }


       public static void loadCourses() {
        courseList.clear();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            JSONArray jsonCourses = new JSONArray(new JSONTokener(reader));

            for (int i = 0; i < jsonCourses.length(); i++) {
                JSONObject obj = jsonCourses.getJSONObject(i);

                String courseId = obj.getString("courseId");
                String title = obj.getString("title");
                String description = obj.getString("description");
                String instructorId = obj.getString("instructorId");

                // Load student IDs
                JSONArray studentArray = obj.getJSONArray("studentIds");
                ArrayList<String> studentIds = new ArrayList<>();
                for (int j = 0; j < studentArray.length(); j++) {
                    studentIds.add(studentArray.getString(j));
                }

                // Load lessons
                JSONArray lessonArray = obj.getJSONArray("lessons");
                ArrayList<Lesson> lessons = new ArrayList<>();
                for (int k = 0; k < lessonArray.length(); k++) {
                    JSONObject l = lessonArray.getJSONObject(k);
                    String lessonId = l.getString("lessonId");
                    String titleL = l.getString("title");
                    String content = l.getString("content");

                    JSONArray resArray = l.getJSONArray("resources");
                    ArrayList<String> resources = new ArrayList<>();
                    for (int r = 0; r < resArray.length(); r++) {
                        resources.add(resArray.getString(r));
                    }

                    Lesson lesson = new Lesson(lessonId, titleL, content, resources);
                    lessons.add(lesson);
                }

                Course course = new Course(courseId, title, description, instructorId, lessons, studentIds);
                courseList.add(course);
            }
        } catch (Exception e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }
           public static void saveCourses() {
        JSONArray jsonCourses = new JSONArray();

        for (Course course : courseList) {
            JSONObject obj = new JSONObject();
            obj.put("courseId", course.getCourseId());
            obj.put("title", course.getTitle());
            obj.put("description", course.getDescription());
            obj.put("instructorId", course.getInstructorId());
            obj.put("studentIds", new JSONArray(course.getStudentIds()));

            JSONArray lessonArray = new JSONArray();
            for (Lesson lesson : course.getLessons()) {
                JSONObject l = new JSONObject();
                l.put("lessonId", lesson.getLessonId());
                l.put("title", lesson.getTitle());
                l.put("content", lesson.getContent());
                l.put("resources", new JSONArray(lesson.getResources()));
                lessonArray.put(l);
            }

            obj.put("lessons", lessonArray);
            jsonCourses.put(obj);
        }

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(jsonCourses.toString(2)); // Pretty print
        } catch (Exception e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    public static boolean addCourse(Course course) {
        for (Course c : courseList) {
            if (c.getCourseId().equals(course.getCourseId())) return false;
        }
        courseList.add(course);
        saveCourses();
        return true;
    }

    public static Course getCourseById(String courseId) {
        for (Course c : courseList) {
            if (c.getCourseId().equals(courseId)) return c;
        }
        return null;
    }


}
