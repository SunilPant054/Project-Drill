package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.entity.Seat;
import com.pantsunil.project_drill.respository.SeatRepository;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

public class SeatService {

    private final SeatRepository seatRepository;

    //constructor
    public SeatService(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    //methods
    public Seat saveSeat(Seat seat){
        return seatRepository.save(seat);
    }

    public List<Seat> getAllSeats(){
        return seatRepository.findAll();
    }

    public Seat getSeatById(Integer id){
        return seatRepository.findById(id).orElse(null);
    }

    public void deleteHall(Integer id){
        seatRepository.deleteById(id);
    }
}
