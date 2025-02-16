package com.pantsunil.project_drill.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "seat_id")
    private int seatID;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "show_id", referencedColumnName = "id")
    private Show show;

    //relation with seat
    @OneToMany(mappedBy = "ticket")
    private List<Seat> seats;

    //constructor
    public Ticket() {

    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatID=" + seatID +
                ", price=" + price +
                ", status=" + status +
                ", seats=" + seats +
                '}';
    }
}
