package com.pantsunil.project_drill.respository;

import com.pantsunil.project_drill.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
