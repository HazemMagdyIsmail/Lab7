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
public class Lesson {
  
    private String lessonId;
    private String title;
    private String content;
    private ArrayList<String> resources;

    public Lesson(String lessonId, String title, String content, ArrayList<String> resources) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = resources;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<String> getResources() {
        return resources;
    }
            public void update(String newTitle, String newContent, ArrayList<String> newResources) {
    this.title = newTitle;
    this.content = newContent;
    this.resources = newResources;
}
    

}
