package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TheaterScheduleService {


    @Autowired
    TheaterSchedule theaterSchedule;

    public List<Showing> getMovieSchedule() {
        return theaterSchedule.getMovieSchedule();
    }


    public Showing getShowBySequence(int sequence) {
        if(sequence >  theaterSchedule.getMovieSchedule().size()) {
            log.error("Unable to find sequence {}", sequence);
            return null;
        }

        return theaterSchedule.getMovieSchedule().get(sequence-1);
    }
}
