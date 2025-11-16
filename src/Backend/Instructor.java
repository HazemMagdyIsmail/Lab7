/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hazem
 */
public class Instructor extends User {
    private List<Course> createdCourses;

    public Instructor( String userId, String role, String username, String email, String passwordHash,List<Course> createdCourses) {
        super(userId, role, username, email, passwordHash);
        this.createdCourses = createdCourses;
    }

   

}
