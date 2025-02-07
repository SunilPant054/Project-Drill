package com.pantsunil.project_drill.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "hall_id")
    private int hallId;

    //relation with seat
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    //bidirectional relation
    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    //Constructor
    public Screen(int id, Hall hall, List<Seat> seats, int hallId, int numberOfSeats) {
        this.id = id;
        this.hall = hall;
        this.seats = seats;
        this.hallId = hallId;
        this.numberOfSeats = numberOfSeats;
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getHall_id() {
        return hallId;
    }

    public void setHall_id(int hall_id) {
        this.hallId = hall_id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    //tostring method

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", numberOfSeats=" + numberOfSeats +
                ", hallId=" + hallId +
                ", seats=" + seats +
                ", hall=" + hall +
                '}';
    }
}
