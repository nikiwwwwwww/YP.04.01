package com.example.itogproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Tasks;
    @NotEmpty(message = "Field cannot be empty")
    private String topic;
    @NotEmpty(message = "Field cannot be empty")
    private String description;
    @NotEmpty(message = "Field cannot be empty")
    private String complexity;

    @Temporal(TemporalType.DATE)
    private Date dateOfIssue;

    @Temporal(TemporalType.DATE)
    private Date dateOfEnd;

    public Tasks(Long ID_Tasks, String topic, String description, String complexity, Date dateOfIssue, Date dateOfEnd) {
        this.ID_Tasks = ID_Tasks;
        this.topic = topic;
        this.description = description;
        this.complexity = complexity;
        this.dateOfIssue = dateOfIssue;
        this.dateOfEnd = dateOfEnd;
    }

    public Tasks() {

    }

    public Long getId() {
        return ID_Tasks;
    }

    public void setId(Long ID_Tasks) {
        this.ID_Tasks = ID_Tasks;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

}
