package com.example.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table (name="todos")
public class TodoItem {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "todo_id")
    private Integer id;
    public Integer getId(){ return id; }


    private Integer uid;
    public void setUid(Integer uid){ this.uid = uid; }
    public Integer getUid(){ return uid; }


    @NotEmpty
    private String description;
    public void setDescription(String desc){ this.description = desc; }
    public String getDescription(){ return description; }


    private Boolean completed;
    public void setStatus(Boolean completed){ this.completed = completed; }
    public Boolean getStatus(){ return completed; }



}
