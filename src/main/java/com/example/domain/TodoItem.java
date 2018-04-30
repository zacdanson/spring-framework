package com.example.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table (name="todos")
public class TodoItem {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "todo_id")
    private Integer id;
    public Integer getId(){ return id; }

    @NotEmpty
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @NotEmpty
    private String description;
    public void setDescription(String desc){ this.description = desc; }
    public String getDescription(){ return description; }

    private String status;
    public void setStatus(String status){ this.status = status; }
    public String getStatus(){ return status; }

    @Override
    public String toString() {
        return "{" +
                    "id: " + this.id +
                    ", description: " + this.description +
                    ", status: " + this.status +
                    ", created by: " + this.name +
                    ", created at: " + this.added +
                    ", time to complete: " + this.timeToComplete +
                "}";
    }

    private Date added;

    public void setDate(Date time){
        this.added = time;
    }

    public Date getDate(){
        return added;
    }

    @Column(name="time_to_complete")
    private Integer timeToComplete;
    public void setTimeToComplete(Integer days){
        this.timeToComplete = days;
    }

    public Integer getTimeToComplete(){
        return timeToComplete;
    }


}
