package com.pantsunil.project_drill.service;

import com.pantsunil.project_drill.dto.ticketdtos.TicketRequestDTO;
import com.pantsunil.project_drill.dto.ticketdtos.TicketResponseDTO;
import com.pantsunil.project_drill.entity.Show;
import com.pantsunil.project_drill.entity.Ticket;
import com.pantsunil.project_drill.exception.IdNotFoundException;
import com.pantsunil.project_drill.respository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Show show = showService.getShowById(ticketRequestDTO.getShowId());


        Ticket ticket = new Ticket();

        ticket.setSeatId(ticketRequestDTO.getSeatId());
        ticket.setPrice(ticketRequestDTO.getPrice());
        ticket.setStatus(ticketRequestDTO.getStatus());

        Ticket savedTicket = ticketRepository.save(ticket);

        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setHallId(show.getHallId());
        ticketResponseDTO.setScreenId(show.getScreenID());
        ticketResponseDTO.setMovieName(show.getMovie().getMovieName());
        ticketResponseDTO.setSeatId(savedTicket.getSeatId());
        ticketResponseDTO.setPrice(savedTicket.getPrice());
        ticketResponseDTO.setStartTime(show.getStartTime());
        ticketResponseDTO.setEndTime(show.getEndTime());
        ticketResponseDTO.setStatus(savedTicket.getStatus());

        return ticketResponseDTO;
    }

    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

//    public List<TicketResponseDTO> getAllTickets(){
//        List<Ticket> tickets = ticketRepository.findAll();
//
//
//        List<TicketResponseDTO> ticketDTO = tickets.stream()
//                .map(ticket -> { ticket.get
//                    TicketResponseDTO dto = new TicketResponseDTO();
//                    dto.setHallId(ticket.getShow().getHallId());
//                    dto.setScreenId(ticket.getShow().getScreenID());
//                    dto.setSeatId(ticket.getSeatId());
//                    dto.setPrice(ticket.getPrice());
//                    dto.setStartTime(ticket.getShow().getStartTime());
//                    dto.setEndTime(ticket.getShow().getEndTime());
//                    dto.setMovieName(ticket.getShow().getMovie().getMovieName());
//                    dto.setStatus(ticket.getStatus());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//
//        return ticketDTO;
//    }

    public Ticket getTicketById(Integer id){
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Ticket with the given id not found!!"));
    }

    public void deleteTicket(Integer id){
        ticketRepository.deleteById(id);
    }

}
