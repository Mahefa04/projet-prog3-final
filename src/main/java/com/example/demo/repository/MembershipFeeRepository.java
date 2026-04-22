package com.example.demo.repository;

import com.example.demo.model.MembershipFee;
import java.util.List;

public interface MembershipFeeRepository {
    MembershipFee save(String collectivityId, MembershipFee membershipFee);
    List<MembershipFee> findByCollectivityId(String collectivityId);
}