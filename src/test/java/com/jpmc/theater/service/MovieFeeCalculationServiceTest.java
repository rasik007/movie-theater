package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieFeeCalculationServiceTest {

    @Autowired
    MovieFeeCalculationService movieFeeCalculationService;

    @Test
    public void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(10, movieFeeCalculationService.calculateTicketPrice(showing));
    }


    @Test
    public void timeBaseDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        assertEquals(2.5, movieFeeCalculationService.getDiscountByShowTime(showing));

        spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        assertEquals(0, movieFeeCalculationService.getDiscountByShowTime(showing));

    }

    @Test
    public void sequenceCodeBasedDiscount() {

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        assertEquals(7.0, movieFeeCalculationService.calculateTicketPrice(showing));

        spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        assertEquals(8.0, movieFeeCalculationService.calculateTicketPrice(showing));

        spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        assertEquals(9.0, movieFeeCalculationService.calculateTicketPrice(showing));

    }


}
