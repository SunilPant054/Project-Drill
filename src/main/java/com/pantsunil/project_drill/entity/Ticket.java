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
    private int seatId;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private String status;

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
        return seatId;
    }

    public void setSeatID(int seatId) {
        this.seatId = seatId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatID=" + seatId +
                ", price=" + price +
                ", status=" + status +
                ", seats=" + seats +
                '}';
    }
}
