package com.jpmc.theater.service;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TheaterReservationService {


    @Autowired
    TheaterScheduleService theaterScheduleService;

    @Autowired
    MovieFeeCalculationService movieFeeCalculationService;

    public Reservation reserveTickets(Customer customer, int sequence, int howManyTickets) {
        Showing showing = theaterScheduleService.getShowBySequence(sequence);
        return new Reservation(customer, showing, howManyTickets);
    }

    public BigDecimal getTotalFeeByReservation(Reservation reservation) {
      return movieFeeCalculationService.calculateFee(reservation);
    }

}
