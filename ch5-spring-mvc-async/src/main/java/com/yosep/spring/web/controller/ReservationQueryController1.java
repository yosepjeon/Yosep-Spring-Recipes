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
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/reservation-query1")
public class ReservationQueryController1 {
    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;

    public ReservationQueryController1(ReservationService reservationService, TaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public void setupForm() {
    }

    @PostMapping
    public Callable<String> sumbitForm(@RequestParam("courtName") String courtName, Model model) {
        return () -> {
            List<Reservation> reservations = Collections.emptyList();
            if (courtName != null) {
                Delayer.randomDelay();  // 서비스를 일부러 지연시킨다.
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);

            return "reservationQuery";
        };
    }

    @GetMapping(params = {"courtName"})
    public ResponseBodyEmitter find(@RequestParam("courtName") String courtName) {
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        taskExecutor.execute(() -> {
            List<Reservation> reservations = reservationService.query(courtName);
            try{
                for(Reservation reservation : reservations) {
                    emitter.send(reservation);
                }
            }catch (IOException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
