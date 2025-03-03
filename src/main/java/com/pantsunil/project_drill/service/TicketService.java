package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.ticketdtos.TicketRequestDTO;
import com.pantsunil.project_drill.dto.ticketdtos.TicketResponseDTO;
import com.pantsunil.project_drill.entity.Ticket;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ShowService showService;

    //constructor
    public TicketService(TicketRepository ticketRepository, ShowService showService) {
        this.ticketRepository = ticketRepository;
        this.showService = showService;
    }

    public TicketResponseDTO saveTicket(TicketRequestDTO ticketRequestDTO){
        Ticket ticket = new Ticket();

        ticket.setShow(showService.getShowById(ticketRequestDTO.getShowId()));
        ticket.setSeatID(ticketRequestDTO.getSeatId());
        ticket.setPrice(ticketRequestDTO.getPrice());
        ticket.setStatus(ticketRequestDTO.getStatus());

        Ticket savedTicket = ticketRepository.save(ticket);

        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setHallId(savedTicket.getShow().getHallId());
        ticketResponseDTO.setScreenId(savedTicket.getShow().getScreenID());
        ticketResponseDTO.setMovieName(savedTicket.getShow().getMovie().getMovieName());
        ticketResponseDTO.setSeatId(savedTicket.getSeatID());
        ticketResponseDTO.setPrice(savedTicket.getPrice());
        ticketResponseDTO.setStartTime(savedTicket.getShow().getStartTime());
        ticketResponseDTO.setEndTime(savedTicket.getShow().getEndTime());
        ticketResponseDTO.setStatus(savedTicket.getStatus());

        return ticketResponseDTO;
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
