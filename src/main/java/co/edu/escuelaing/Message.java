package co.edu.escuelaing;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    
    @Field(name = "message")
    private String content;
    
    @Field(name = "date")
    private Date date;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}





