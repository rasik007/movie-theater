package com.jpmc.theater.service;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TheaterReservationServiceTest {

    @Autowired
    TheaterReservationService theaterReservationService;

    @Test
    public void testReservation() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))
        );
        Reservation reservation = new Reservation(customer,showing,3);
        assertTrue(theaterReservationService.getTotalFeeByReservation(reservation) == 28.5);

    }

    @Test
    void totalFeeForCustomer() {
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theaterReservationService.reserveTickets(john, 2, 4);
        assertEquals(theaterReservationService.getTotalFeeByReservation(reservation), 37.5);
    }

}
