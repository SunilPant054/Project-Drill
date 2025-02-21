package com.pantsunil.project_drill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hall_name")
    private String hallName;

    @Column(name = "location")
    private String location;

    //relation
    @OneToMany(mappedBy = "hall")
    private List<Screen> screens;

    //relation
    @OneToMany(mappedBy = "hall")
    private List<MovieHall> movieHalls;

    //constructor
    public Hall() {

    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    // to string method
    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", hallName='" + hallName + '\'' +
                ", location='" + location + '\'' +
                ", screens=" + screens +
                '}';
    }
}
