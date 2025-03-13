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

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
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


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatId=" + seatId +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
