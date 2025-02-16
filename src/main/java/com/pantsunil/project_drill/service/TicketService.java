package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.entity.Ticket;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    //constructor
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Integer id){
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Ticket with the given id not found!!"));
    }

    public void deleteTicket(Integer id){
        ticketRepository.deleteById(id);
    }

}
