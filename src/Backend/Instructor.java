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
    private ArrayList<String> createdCourses;

    public Instructor( String userId, String role, String username, String email, String passwordHash,ArrayList<String> createdCourses) {
        super(userId, role, username, email, passwordHash);
        this.createdCourses = createdCourses;
    }

    public ArrayList<String> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(ArrayList<String> createdCourses) {
        this.createdCourses = createdCourses;
    }

   

}
