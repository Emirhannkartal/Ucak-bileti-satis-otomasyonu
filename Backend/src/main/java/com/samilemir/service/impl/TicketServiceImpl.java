package com.samilemir.service.impl;

import com.samilemir.exception.BaseException;
import com.samilemir.exception.ErrorMessage;
import com.samilemir.exception.MessageType;
import com.samilemir.model.Seat;
import com.samilemir.model.Ticket;
import com.samilemir.repos.SeatRepos;
import com.samilemir.repos.TicketRepos;
import com.samilemir.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private TicketRepos ticketRepository;

    @Autowired
    private SeatRepos seatRepository; // Koltukları kontrol etmek için eklendi

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage("Ticket not found with ID: " + id, MessageType.BILET_BULUNAMADI)));
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        // Varsayılan değerler
        ticket.setAmount(1000.00); // Varsayılan tutar
        ticket.setCurrencyType("USD"); // Varsayılan döviz tipi
        ticket.setTicketStatus("CONFIRMED"); // Varsayılan durum

        // Rastgele PNR oluştur
        ticket.setPnrCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        // Flight ID ile uygun bir koltuk bulun
        List<Seat> availableSeats = seatRepository.findByFlightIdAndIsAvailable(ticket.getFlight().getId(), true);
        if (availableSeats.isEmpty()) {
            throw new RuntimeException("No available seats for the flight");
        }

        // İlk uygun koltuğu al ve ilişkilendir
        Seat selectedSeat = availableSeats.get(0);
        selectedSeat.setAvailable(false); // Koltuğu rezerve et
        seatRepository.save(selectedSeat); // Koltuk durumunu güncelle

        ticket.setSeatNumber(selectedSeat.getSeatNumber()); // Koltuk numarasını bilete ata

        return ticketRepository.save(ticket); // Bileti kaydet
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setSeatNumber(ticketDetails.getSeatNumber());
        existingTicket.setPassenger(ticketDetails.getPassenger());
        existingTicket.setFlight(ticketDetails.getFlight());
        return ticketRepository.save(existingTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }
}
