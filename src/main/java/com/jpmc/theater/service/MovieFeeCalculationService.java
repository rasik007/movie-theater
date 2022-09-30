package com.jpmc.theater.service;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class MovieFeeCalculationService {

    private static int MOVIE_CODE_SPECIAL = 1;

    public BigDecimal calculateFee(Showing showing, int ticketCount) {
       return calculateTicketPrice(showing).multiply(new BigDecimal(ticketCount));
    }

    public BigDecimal calculateTicketPrice(Showing showing) {
        double movieTicketPrice =  showing.getMovie().getTicketPrice();
        int specialCode = showing.getMovie().getSpecialCode();
        BigDecimal discount = getDiscount(showing, showing.getSequenceOfTheDay(),specialCode,movieTicketPrice);
        return new BigDecimal(movieTicketPrice).subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getDiscount(Showing showing, int showSequence, int specialCode , double ticketPrice) {


        BigDecimal maxDiscount = BigDecimal.ZERO;
        BigDecimal specialDiscount = getDiscountBySpecialCode(specialCode, ticketPrice);

        maxDiscount = specialDiscount;

        BigDecimal sequenceDiscount = getDiscountBySequence(showSequence);

        if(sequenceDiscount.compareTo(maxDiscount) > 0) {
            maxDiscount = sequenceDiscount;
        }

        BigDecimal showTimeDiscount = getDiscountByShowTime(showing);
        if(showTimeDiscount.compareTo( maxDiscount) >0) {
            maxDiscount = showTimeDiscount;
        }
        return maxDiscount;
    }


    public BigDecimal getDiscountBySpecialCode(int specialCode, double ticketPrice) {
        double specialDiscount = 0;

        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        return new BigDecimal(specialDiscount).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountBySequence(int showSequence) {
        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if (showSequence == 7) {
            sequenceDiscount = 1;
        }
        return new BigDecimal(sequenceDiscount);
    }

    public BigDecimal getDiscountByShowTime(Showing showing) {
        double showTimeDiscount = 0;

        LocalDateTime localTime = showing.getShowStartTime();
        int hour =  localTime.getHour();

        if(hour>=11 &&  hour<=16) {
            showTimeDiscount = showing.getMovie().getTicketPrice() * 0.25;
        }
        return new BigDecimal(showTimeDiscount).setScale(2,RoundingMode.HALF_UP);
    }

    public BigDecimal calculateFee(Reservation reservation) {
        return  calculateFee(reservation.getShowing(),reservation.getAudienceCount());
    }
}
