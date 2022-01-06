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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/reservation-query1")
public class ReservationQueryController3 {
    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;

    public ReservationQueryController3(ReservationService reservationService, TaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public void setupForm() {}

    @PostMapping
    public CompletableFuture<String> submitForm(@RequestParam("courtName") String courtName, Model model) {
        return CompletableFuture.supplyAsync(() -> {
            List<Reservation> reservations = Collections.emptyList();

            if(courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);
            return "reservationQuery";
        }, taskExecutor);
    }

    @GetMapping(params = {"courtName"})
    public SseEmitter find(@RequestParam("courtName") String courtName) {
        final SseEmitter emitter = new SseEmitter();

        taskExecutor.execute(() -> {
            List<Reservation> reservations = reservationService.query(courtName);
            try{
                for(Reservation reservation : reservations) {
                    Delayer.delay(15);
                    emitter.send(reservation);
                }
                emitter.complete();
            }catch (IOException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
