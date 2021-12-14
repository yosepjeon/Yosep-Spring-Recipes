package com.yosep.spring.web;

import com.yosep.spring.Delayer;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/reservation-query")
public class ReservationQueryController {
    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;

    public ReservationQueryController(ReservationService reservationService, TaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public void setupForm() {}

    @PostMapping
    public CompletableFuture<String> sumbitForm(@RequestParam("courtName") String courtName, Model model) {
        return CompletableFuture.supplyAsync(() -> {
            List<Reservation> reservations = Collections.emptyList();
            if(courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }

            model.addAttribute("reservations", reservations);

            return "reservationQuery";
        });
    }
}
