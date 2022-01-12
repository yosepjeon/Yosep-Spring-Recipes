package com.yosep.spring.web.service;

import com.yosep.spring.web.domain.Reservation;
import com.yosep.spring.web.domain.SportType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class InMemoryReservationService implements ReservationService{
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
        return null;
    }

    @Override
    public SportType getSportType(int sportTypeId) {
        return null;
    }
}
