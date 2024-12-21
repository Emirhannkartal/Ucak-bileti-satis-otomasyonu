package com.samilemir.controller;

import com.samilemir.dto.DtoPassenger;
import com.samilemir.dto.DtoPassengerIU;
import com.samilemir.model.Passenger;
import com.samilemir.model.User;
import com.samilemir.service.IPassengerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    @Autowired
    private IPassengerService passengerService;

    @GetMapping
    public List<DtoPassenger> getAllPassengers() {
        return passengerService.getAllPassengers().stream()
                .map(this::convertToDtoPassenger)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DtoPassenger getPassengerById(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        return convertToDtoPassenger(passenger);
    }

    @GetMapping("/user/{userId}")
    public DtoPassenger getPassengerByUserId(@PathVariable Long userId) {
        Passenger passenger = passengerService.getPassengerByUserId(userId);
        return convertToDtoPassenger(passenger);
    }

    @PostMapping
    public DtoPassenger createPassenger(@RequestBody DtoPassengerIU passengerDto) {
        Passenger passenger = convertToEntity(passengerDto);
        Passenger createdPassenger = passengerService.createPassenger(passenger);
        return convertToDtoPassenger(createdPassenger);
    }

    @PutMapping("/{id}")
    public DtoPassenger updatePassenger(@PathVariable Long id, @RequestBody DtoPassengerIU passengerDto) {
        Passenger passenger = convertToEntity(passengerDto);
        Passenger updatedPassenger = passengerService.updatePassenger(id, passenger);
        return convertToDtoPassenger(updatedPassenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }

    private DtoPassenger convertToDtoPassenger(Passenger passenger) {
        return new DtoPassenger(
                passenger.getId(),
                passenger.getFirstName(),
                passenger.getLastName()
        );
    }

    private Passenger convertToEntity(DtoPassengerIU passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setBirthOfDate(passengerDto.getBirthOfDate());
        passenger.setEMail(passengerDto.getEMail());

        if (passengerDto.getUserId() != null) {
            User user = new User();
            user.setId(passengerDto.getUserId());
            passenger.setUser(user);
        }
        return passenger;
    }
}
