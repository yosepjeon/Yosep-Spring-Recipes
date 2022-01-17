package com.yosep.spring.web.controller;

import com.yosep.spring.Delayer;
import com.yosep.spring.web.domain.Reservation;
import com.yosep.spring.web.service.ReservationService1;
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
@RequestMapping("/reservation-query3")
public class ReservationQueryController3 {
    private final ReservationService1 reservationService1;
    private final TaskExecutor taskExecutor;

    public ReservationQueryController3(ReservationService1 reservationService1, TaskExecutor taskExecutor) {
        this.reservationService1 = reservationService1;
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
                reservations = reservationService1.query(courtName);
            }

            model.addAttribute("reservations", reservations);
            return "reservationQuery";
        }, taskExecutor);
    }

    @GetMapping(params = {"courtName"})
    public SseEmitter find(@RequestParam("courtName") String courtName) {
        final SseEmitter emitter = new SseEmitter();

        taskExecutor.execute(() -> {
            List<Reservation> reservations = reservationService1.query(courtName);
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
