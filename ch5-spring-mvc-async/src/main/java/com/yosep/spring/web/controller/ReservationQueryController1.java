package com.yosep.spring.web.controller;

import com.yosep.spring.Delayer;
import com.yosep.spring.web.domain.Reservation;
import com.yosep.spring.web.service.ReservationService;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/reservation-query")
public class ReservationQueryController1 {
    private final ReservationService reservationService;

    public ReservationQueryController1(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public void setupForm() {}

    @PostMapping
    public Callable<String> sumbitForm(@RequestParam("courtName") String courtName, Model model) {
        return () -> {
            List<Reservation> reservations = Collections.emptyList();
            if(courtName != null) {
                Delayer.randomDelay();  // 서비스를 일부러 지연시킨다.
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);

            return "reservationQuery";
        };
    }
}
