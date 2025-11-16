/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hazem
 */
public class Student extends User {
    private ArrayList<String> enrolledCourses;
    private HashMap<String, ArrayList<String>> progress;
    public Student(String userId, String role, String username, String email, String passwordHash,
                   ArrayList<String> enrolledCourses, HashMap<String, ArrayList<String>> progress) {
        super(userId, role, username, email, passwordHash);
        this.enrolledCourses = enrolledCourses;
        this.progress = progress;
    }

    public Student(String userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
        
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public HashMap<String, ArrayList<String>> getProgress() {
        return progress;
    }

    public void setProgress(HashMap<String, ArrayList<String>> progress) {
        this.progress = progress;
    }
    

   
    
      

}
