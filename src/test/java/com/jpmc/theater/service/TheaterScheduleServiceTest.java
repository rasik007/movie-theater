package com.jpmc.theater.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TheaterScheduleServiceTest {

    @Autowired
    TheaterScheduleService theaterScheduleService;

    @Test
    public void testTheaterSchedule() {

        assertTrue(theaterScheduleService.getMovieSchedule().get(0).getMovie().getSpecialCode() == 0);
        assertTrue(theaterScheduleService.getMovieSchedule().get(1).getMovie().getSpecialCode() == 1);
    }
}
