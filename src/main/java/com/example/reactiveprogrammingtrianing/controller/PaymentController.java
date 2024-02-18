package com.example.reactiveprogrammingtrianing.controller;

import com.example.reactiveprogrammingtrianing.model.CreateAccountResponse;
import com.example.reactiveprogrammingtrianing.service.PaymentService;
import com.example.reactiveprogrammingtrianing.model.CreateAccountRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/account")
    public Mono<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return paymentService.createAccount(createAccountRequest);
    }
}
