/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hazem
 */
public class UserJsonDatabase {

    private String fileName = "User.JSON";
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Instructor> Instructors = new ArrayList<>();

    public UserJsonDatabase() throws IOException {

        readFromFile();

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Instructor> getInstructors() {
        return Instructors;
    }

    public void setInstructors(ArrayList<Instructor> Instructors) {
        this.Instructors = Instructors;
    }

public void saveToFile() throws IOException {
    JSONArray usersArray = new JSONArray();

    for (Student s : students) {
        JSONObject obj = new JSONObject();

        obj.put("userId", s.getUserId());
        obj.put("role", s.getRole());
        obj.put("username", s.getUsername());
        obj.put("email", s.getEmail());
        obj.put("passwordHash", s.getPasswordHash());

        
        JSONArray enrolledCoursesArray = new JSONArray();
        if (s.getEnrolledCourses() != null) {
            for (String courseId : s.getEnrolledCourses()) {
                JSONObject courseObj = new JSONObject();
                courseObj.put("courseId", courseId);
                enrolledCoursesArray.put(courseObj);
            }
        }
        obj.put("enrolledCourses", enrolledCoursesArray);

        
        JSONObject progressObj = new JSONObject();
        if (s.getProgress() != null) {
            for (String courseId : s.getProgress().keySet()) {
                JSONArray lessonsArray = new JSONArray();
                for (String lessonId : s.getProgress().get(courseId)) {
                    JSONObject lessonObj = new JSONObject();
                    lessonObj.put("lessonId", lessonId);
                    lessonsArray.put(lessonObj);
                }
                progressObj.put(courseId, lessonsArray);
            }
        }
        obj.put("progress", progressObj);

        usersArray.put(obj);
    }

    for (Instructor ins : Instructors) {
        JSONObject obj = new JSONObject();

        obj.put("userId", ins.getUserId());
        obj.put("role", ins.getRole());
        obj.put("username", ins.getUsername());
        obj.put("email", ins.getEmail());
        obj.put("passwordHash", ins.getPasswordHash());

        JSONArray createdCoursesArray = new JSONArray();
        if (ins.getCreatedCourses() != null) {
            for (String courseId : ins.getCreatedCourses()) {
                JSONObject courseObj = new JSONObject();
                courseObj.put("courseId", courseId);
                createdCoursesArray.put(courseObj);
            }
        }
        obj.put("createdCourses", createdCoursesArray);

        usersArray.put(obj);
    }

    Files.write(Paths.get("data.json"), usersArray.toString(4).getBytes()); // 4 for pretty print
}

  public void readFromFile() throws IOException {

    String content = new String(Files.readAllBytes(Paths.get("data.json")));
    JSONArray userJson = new JSONArray(content);

    for (int i = 0; i < userJson.length(); i++) {

        JSONObject obj = userJson.getJSONObject(i);

        String userId = obj.getString("userId");
        String role = obj.getString("role");
        String username = obj.getString("username");
        String email = obj.getString("email");
        String passwordHash = obj.getString("passwordHash");

      
        if (role.equalsIgnoreCase("Student")) {

           
            JSONArray coursesArray = obj.optJSONArray("enrolledCourses");
            ArrayList<String> enrolledCourses = new ArrayList<>();

            if (coursesArray != null) {
                for (int j = 0; j < coursesArray.length(); j++) {
                    JSONObject courseObj = coursesArray.getJSONObject(j);
                    enrolledCourses.add(courseObj.getString("courseId"));
                }
            }

        
            JSONObject progressObj = obj.optJSONObject("progress");
            HashMap<String, ArrayList<String>> progressMap = new HashMap<>();

            if (progressObj != null) {
                for (String courseId : progressObj.keySet()) {

                    JSONArray lessonsArray = progressObj.optJSONArray(courseId);
                    ArrayList<String> lessonIds = new ArrayList<>();

                    if (lessonsArray != null) {
                        for (int k = 0; k < lessonsArray.length(); k++) {
                            JSONObject lessonObj = lessonsArray.getJSONObject(k);
                            lessonIds.add(lessonObj.getString("lessonId"));
                        }
                    }

                    progressMap.put(courseId, lessonIds);
                }
            }

            students.add(new Student(
                userId, role, username, email, passwordHash,
                enrolledCourses, progressMap
            ));
        }

       
        else if (role.equalsIgnoreCase("Instructor")) {

            JSONArray coursesArray = obj.optJSONArray("createdCourses");
            ArrayList<String> createdCourses = new ArrayList<>();

            if (coursesArray != null) {
                for (int j = 0; j < coursesArray.length(); j++) {
                    JSONObject courseObj = coursesArray.getJSONObject(j);
                    createdCourses.add(courseObj.getString("courseId"));
                }
            }

            Instructors.add(new Instructor(
                userId, role, username, email, passwordHash,
                createdCourses
            ));
        }
    }
}
public boolean deleteInstructor(String userId) {
    Instructor i = getInstructorById(userId);
    if (i != null) {
        Instructors.remove(i);
        return true;
    } else {
        System.out.println("Instructor not found: " + userId);
        return false;
    }
}
 
public boolean containsStudent(String userId) {
    for (Student s : students) {
        if (s.getUserId().equals(userId)) {
            return true;
        }
    }
    return false;
}

// Check if an instructor with given userId exists
public boolean containsInstructor(String userId) {
    for (Instructor i : Instructors) {
        if (i.getUserId().equals(userId)) {
            return true;
        }
    }
    return false;
}

public Student getStudentById(String userId) {
    for (Student s : students) {
        if (s.getUserId().equals(userId)) {
            return s;
        }
    }
    return null;
}

// Returns the Instructor object with the given userId, or null if not found
public Instructor getInstructorById(String userId) {
    for (Instructor i : Instructors) {
        if (i.getUserId().equals(userId)) {
            return i;
        }
    }
    return null;
}

// Search by username
public Student getStudentByUsername(String username) {
    for (Student s : students) {
        if (s.getUsername().equalsIgnoreCase(username)) {
            return s;
        }
    }
    return null;
}

public Instructor getInstructorByUsername(String username) {
    for (Instructor i : Instructors) {
        if (i.getUsername().equalsIgnoreCase(username)) {
            return i;
        }
    }
    return null;
}


public boolean addStudent(Student s) throws IOException {
    if (!containsStudent(s.getUserId())) {
        students.add(s);
          saveToFile();
        return true; 
    } else {
        System.out.println("Student already exists: " + s.getUserId());
        return false; 
    }
   
}



public boolean deleteStudent(String userId) throws IOException {
    Student s = getStudentById(userId);
    if (s != null) {
        students.remove(s);
          saveToFile();
        return true; // successfully removed
    } else {
        System.out.println("Student not found: " + userId);
        return false;
    }
}



 public boolean addInstructor(Instructor i) throws IOException {
    if (!containsInstructor(i.getUserId())) {
        Instructors.add(i);
        saveToFile();
        return true;
    } else {
        System.out.println("Instructor already exists: " + i.getUserId());
        return false;
    }
}
public boolean validateLoginStudent(String username, String password) {
   
    for (Student s : students) {
        if (s.getUsername().equals(username) && s.getPasswordHash().equals(password)) {
            return true;
        }
    }

 
    return false;
}
public boolean validateLoginInstructor(String username, String password) {
    

    
    for (Instructor i : Instructors) {
        if (i.getUsername().equals(username) && i.getPasswordHash().equals(password)) {
            return true;
        }
    }


    return false;
}
public static String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("SHA-256");
        byte[] hashbytes = m.digest(password.getBytes());
        String s = "";
        for (byte hashbyte : hashbytes) {
            s += String.format("%02x", hashbyte);

        }
        return s;

    }
}
