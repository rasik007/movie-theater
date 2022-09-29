package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Movie;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class TheaterSchedule {

    private List<Showing> schedule;

    @PostConstruct
    private void init() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)))
        );

    }

    public List<Showing> getMovieSchedule() {
        return schedule;
    }
}
