package com.example.demo.service;

import com.example.demo.endpoint.rest.model.CreateMemberPaymentRest;
import com.example.demo.model.MemberPayment;
import com.example.demo.repository.MemberPaymentRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final MemberPaymentRepository memberPaymentRepository;

    public PaymentService(MemberPaymentRepository memberPaymentRepository) {
        this.memberPaymentRepository = memberPaymentRepository;
    }

    public List<MemberPayment> createPayments(String memberId, List<CreateMemberPaymentRest> requests) {
        List<MemberPayment> payments = new ArrayList<MemberPayment>();

        for (int i = 0; i < requests.size(); i++) {
            CreateMemberPaymentRest request = requests.get(i);

            MemberPayment payment = new MemberPayment();

            payment.setId("pay-" + UUID.randomUUID().toString().substring(0, 8));
            payment.setMemberId(memberId);
            payment.setMembershipFeeId(request.getMembershipFeeIdentifier());
            payment.setAmount(request.getAmount());
            payment.setPaymentMode(request.getPaymentMode());
            payment.setAccountCreditedId(request.getAccountCreditedIdentifier());
            payment.setCreationDate(LocalDate.now());

            payments.add(payment);
        }

        return memberPaymentRepository.saveAll(payments);
    }
}