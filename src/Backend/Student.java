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
    
    private List<Course> enrolledCourses;
    private Map<Course,List<Lesson>> progress;

    public Student( String userId, String role, String username, String email, String passwordHash,List<Course> enrolledCourses, Map<Course, List<Lesson>> progress) {
        super(userId, role, username, email, passwordHash);
        this.enrolledCourses = enrolledCourses;
        this.progress = progress;
    }
    

   
    
      

}
