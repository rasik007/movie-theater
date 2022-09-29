package com.jpmc.theater.service;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class MovieFeeCalculationService {

    private static int MOVIE_CODE_SPECIAL = 1;

    public double calculateFee(Showing showing, int ticketCount) {
       return calculateTicketPrice(showing) * ticketCount;
    }

    public double calculateTicketPrice(Showing showing) {
        double movieTicketPrice =  showing.getMovie().getTicketPrice();
        int specialCode = showing.getMovie().getSpecialCode();
        double discount = getDiscount(showing, showing.getSequenceOfTheDay(),specialCode,movieTicketPrice);
        return movieTicketPrice - discount;
    }

    private double getDiscount(Showing showing, int showSequence, int specialCode , double ticketPrice) {


        double maxDiscount = Double.MIN_VALUE;
        double specialDiscount = getDiscountBySpecialCode(specialCode, ticketPrice);

        maxDiscount = specialDiscount;

        double sequenceDiscount = getDiscountBySequence(showSequence);

        if(sequenceDiscount > maxDiscount) {
            maxDiscount = sequenceDiscount;
        }

        double showTimeDiscount = getDiscountByShowTime(showing);
        if(showTimeDiscount > maxDiscount) {
            maxDiscount = showTimeDiscount;
        }
        return maxDiscount;
    }


    public double getDiscountBySpecialCode(int specialCode, double ticketPrice) {
        double specialDiscount = 0;

        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        return specialDiscount;
    }

    public double getDiscountBySequence(int showSequence) {
        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if (showSequence == 7) {
            sequenceDiscount = 1;
        }
        return sequenceDiscount;
    }

    public double getDiscountByShowTime(Showing showing) {
        double showTimeDiscount = 0;

        LocalDateTime localTime = showing.getShowStartTime();
        int hour =  localTime.getHour();

        if(hour>=11 &&  hour<=16) {
            showTimeDiscount = showing.getMovie().getTicketPrice() * 0.25;
        }
        return showTimeDiscount;
    }

    public double calculateFee(Reservation reservation) {
        return  calculateFee(reservation.getShowing(),reservation.getAudienceCount());
    }
}
