package com.example.demo.endpoint.rest.controller;


import com.example.demo.model.MemberPayment;
import com.example.demo.service.PaymentService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{id}/payments")
    public List<MemberPayment> createPayments(
            @PathVariable String id,
            @RequestBody List<MemberPayment> payments) throws Exception {

        return paymentService.createPayments(id, payments);
    }
}
