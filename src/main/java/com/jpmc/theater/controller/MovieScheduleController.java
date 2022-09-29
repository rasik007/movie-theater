package com.jpmc.theater.controller;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.MovieFeeCalculationService;
import com.jpmc.theater.service.TheaterScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
public class MovieScheduleController {

    @Autowired
    private TheaterScheduleService theaterScheduleService;

    @Autowired
    private MovieFeeCalculationService movieFeeCalculationService;

    @GetMapping("/movieSchedule")
    public List<Showing> getMovieSchedule() {
        log.info("returning movie schedule");
        return theaterScheduleService.getMovieSchedule();
    }

    @GetMapping("/movieScheduleInText")
    public String getMovieScheduleInText() {
        log.info("returning movie schedule");

        StringBuilder sb = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        theaterScheduleService.getMovieSchedule().forEach(s ->
                sb.append("\n")
                .append("MovieName :").append(s.getMovie().getTitle())
                        .append(" Price: ").append(movieFeeCalculationService.calculateTicketPrice(s)).append(" USD")
                        .append(" startTime :").append(s.getShowStartTime().format(formatter)).append(" RunningTime: ").append(s.getMovie().getRunningTime().toMinutes()).append(" mins").append("\n")
        );
        return sb.toString();
    }

}
