package com.samilemir.service.impl;

import com.samilemir.exception.BaseException;
import com.samilemir.exception.ErrorMessage;
import com.samilemir.exception.MessageType;
import com.samilemir.model.Passenger;
import com.samilemir.repos.PassengerRepos;
import com.samilemir.service.IPassengerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements IPassengerService {

    @Autowired
    private PassengerRepos passengerRepos;

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepos.findAll();
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepos.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        "Passenger not found with ID: " + id, MessageType.YOLCU_BULUNAMADI
                )));
    }

    @Override
    public Passenger getPassengerByUserId(Long userId) {
        return passengerRepos.findByUserId(userId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        "Passenger not found for user ID: " + userId, MessageType.YOLCU_BULUNAMADI
                )));
    }

    @Override
    public Passenger createPassenger(Passenger passenger) {
        if (passengerRepos.existsByUserId(passenger.getUser().getId())) {
            throw new BaseException(new ErrorMessage(
                    "",
                    MessageType.YOLCU_ZATEN_KAYITLI
            ));
        }
        return passengerRepos.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Long id, Passenger passengerDetails) {
        Passenger existingPassenger = getPassengerById(id);
        existingPassenger.setFirstName(passengerDetails.getFirstName());
        existingPassenger.setLastName(passengerDetails.getLastName());
        existingPassenger.setBirthOfDate(passengerDetails.getBirthOfDate());
        existingPassenger.setEMail(passengerDetails.getEMail());
        return passengerRepos.save(existingPassenger);
    }

    @Override
    public void deletePassenger(Long id) {
        Passenger passenger = getPassengerById(id);
        passengerRepos.delete(passenger);
    }
}
