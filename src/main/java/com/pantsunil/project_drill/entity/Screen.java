package com.pantsunil.project_drill.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    //relation with seat
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    //bidirectional relation
    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    //Constructor
    public Screen() {

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
                ", seats=" + seats +
                ", hall=" + hall +
                '}';
    }
}
