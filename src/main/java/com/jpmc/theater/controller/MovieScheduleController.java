package com.jpmc.theater.controller;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.TheaterScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MovieScheduleController {

    @Autowired
    private TheaterScheduleService theaterScheduleService;

    @GetMapping("/movieSchedule")
    public List<Showing> getMovieSchedule() {
        log.info("returning movie schedule");
        return theaterScheduleService.getMovieSchedule();
    }


}
