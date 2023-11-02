package com.example.itogproject.models;

import javax.persistence.*;

@Entity
public class EmployeTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Employe_tasks;

    @ManyToOne
    @JoinColumn(name = "tasks_id")
    private Tasks tasks;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    public EmployeTasks(Long ID_Employe_tasks, Tasks tasks, Employe employe) {
        this.ID_Employe_tasks = ID_Employe_tasks;
        this.tasks = tasks;
        this.employe = employe;
    }

    public EmployeTasks() {

    }

    public Long getId() {
        return ID_Employe_tasks;
    }

    public void setId(Long ID_Employe_tasks) {
        this.ID_Employe_tasks = ID_Employe_tasks;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
