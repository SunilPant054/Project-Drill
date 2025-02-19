package com.pantsunil.project_drill.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_halls")
public class MovieHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Relation with movies
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    //Relation with halls
    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    //constructor
    public MovieHall() {

    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    //toString method

    @Override
    public String toString() {
        return "MovieHall{" +
                "id=" + id +
                ", movie=" + movie +
                ", hall=" + hall +
                '}';
    }
}
