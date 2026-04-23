package com.example.demo.repository;

import com.example.demo.model.MemberPayment;
import java.util.List;

public interface MemberPaymentRepository {

    void save(MemberPayment payment);

    List<MemberPayment> findByMemberId(String memberId);
}