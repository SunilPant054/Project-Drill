package com.pantsunil.project_drill.controller;

import com.pantsunil.project_drill.dto.ticketdtos.TicketRequestDTO;
import com.pantsunil.project_drill.dto.ticketdtos.TicketResponseDTO;
import com.pantsunil.project_drill.entity.Ticket;
import com.pantsunil.project_drill.respository.TicketRepository;
import com.pantsunil.project_drill.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TicketController {

    private final TicketService ticketService;

    //constructor
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
//
//    @GetMapping("/tickets")
//    public ResponseEntity<List<TicketResponseDTO>> getTickets(){
//        List<TicketResponseDTO> tickets = ticketService.getAllTickets();
//        return new ResponseEntity<>(tickets, HttpStatus.OK);
//    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable int id){
        Ticket ticket = ticketService.getTicketById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/tickets")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO ticketRequestDTO){
        TicketResponseDTO savedTicket = ticketService.saveTicket(ticketRequestDTO);
        return new ResponseEntity<>(savedTicket, HttpStatus.OK);
    }

    @DeleteMapping("tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int id){
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
