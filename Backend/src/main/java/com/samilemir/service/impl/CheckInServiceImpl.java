package com.samilemir.service.impl;

import com.samilemir.model.Ticket;
import com.samilemir.repos.TicketRepos;
import com.samilemir.service.ICheckInService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInServiceImpl implements ICheckInService {

    @Autowired
    private TicketRepos ticketRepository;

    @Override
    public String checkInPassenger(String lastName, String pnrCode) {
        Ticket ticket = ticketRepository.findByPassengerLastNameAndPnrCode(lastName, pnrCode);
        
        if (ticket == null) {
            return "Bilet bulunamadı. Lütfen soyadınızı ve PNR kodunu kontrol edin.";
        }
        if (ticket.isCheckedIn()) {
            return "Bu bilet için zaten check-in yapılmış.";
        }
        ticket.setCheckedIn(true);
        ticketRepository.save(ticket);

        return "Check-in işlemi başarıyla tamamlandı.";
    }
}
