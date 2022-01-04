package com.yosep.spring.web.service;

import com.yosep.spring.web.domain.Reservation;
import com.yosep.spring.web.domain.SportType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReservationService {

    List<Reservation> query(String query);
    Flux<Reservation> findAll();
    Flux<SportType> getAllSportTypes();
    Mono<Reservation> make(Reservation reservation);

    SportType getSportType(int sportTypeId);
}
