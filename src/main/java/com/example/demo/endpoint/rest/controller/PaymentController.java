package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.model.CreateMemberPaymentRest;
import com.example.demo.model.MemberPayment;
import com.example.demo.service.PaymentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/members/{id}/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MemberPayment> createPayments(
            @PathVariable String id,
            @RequestBody List<CreateMemberPaymentRest> payments
    ) {
        return paymentService.createPayments(id, payments);
    }
}