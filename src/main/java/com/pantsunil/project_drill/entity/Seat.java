package com.pantsunil.project_drill.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "row")
    private int row;

    @Column(name = "column")
    private int column;

    @Column(name = "screen_id")
    private int screenId;

    //bidirectional relation with Screen
    @ManyToOne
    @JoinColumn(name = "screen_id", referencedColumnName = "id")
    private Screen screen;

    //Constructor
    public Seat(int id, int row, int column, int screenId, Screen screen) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.screenId = screenId;
        this.screen = screen;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    //toString method
    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                ", screenId=" + screenId +
                ", screen=" + screen +
                '}';
    }
}
