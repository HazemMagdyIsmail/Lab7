/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hazem
 */
public class UserJsonDatabase {
    private String fileName="User.JSON";
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Instructor> Instructors = new ArrayList<>();

    public UserJsonDatabase() {
    }

    
    public void readFromFile() throws IOException{
   
String content = new String(Files.readAllBytes(Paths.get("data.json")));    
    JSONArray userJson = new JSONArray(content);
    for(int i=0 ;i<userJson.length();i++){
    JSONObject obj = userJson.getJSONObject(i);
    String userId = obj.getString("userId");
        String role = obj.getString("role");
        String username = obj.getString("username");
        String email = obj.getString("email");
        String passwordHash = obj.getString("passwordHash");
        if(role.equalsIgnoreCase(Student)){
        
        
        
        }
        else{
        
        
        
        
        
        
        }
    
    
    
    
    }
    
    }
    
}
