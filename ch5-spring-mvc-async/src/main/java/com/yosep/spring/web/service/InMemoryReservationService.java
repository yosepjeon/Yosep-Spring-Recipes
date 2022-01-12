package com.yosep.spring.web.service;

import com.yosep.spring.web.domain.Player;
import com.yosep.spring.web.domain.Reservation;
import com.yosep.spring.web.domain.SportType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryReservationService implements ReservationService{
    public static final SportType TENNIS = new SportType(1, "Tennis");
    public static final SportType SOCCER = new SportType(2, "Soccer");

    private final List<Reservation> reservations = new ArrayList<>();

    public InMemoryReservationService() {
        reservations.add(new Reservation("Tennis #1", LocalDate.of(2008, 1, 14), 16, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #2", LocalDate.of(2008, 1, 14), 20, new Player("Roger", "N/A"), TENNIS));
    }

    @Override
    public List<Reservation> query(String query) {
        return null;
    }

    @Override
    public Flux<Reservation> findAll() {
        return null;
    }

    @Override
    public Flux<SportType> getAllSportTypes() {
        return null;
    }

    @Override
    public Mono<Reservation> make(Reservation reservation) {
        long cnt = reservations.stream()
                .filter(made -> ObjectUtils.nullSafeEquals(made.getCourtName(), reservation.getCourtName()))
                .filter(made -> ObjectUtils.nullSafeEquals(made.getDate(), reservation.getDate()))
                .filter(made -> made.getHour() == reservation.getHour())
                .count();

        if(cnt > 0) {
            return Mono.error(new ReservationNotAvailableException(reservation.getCourtName(), reservation.getDate(), reservation.getHour()));
        }else {
            reservations.add(reservation);
            return Mono.just(reservation);
        }

        return null;
    }

    @Override
    public SportType getSportType(int sportTypeId) {
        return null;
    }
}
