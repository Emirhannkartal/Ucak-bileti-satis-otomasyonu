package com.samilemir.service;

import com.samilemir.model.Ticket;
import java.util.List;

public interface ITicketService {
    List<Ticket> getAllTickets();

    Ticket getTicketById(Long id);

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Long id, Ticket ticketDetails);

    void deleteTicket(Long id);
}
