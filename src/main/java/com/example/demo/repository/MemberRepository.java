package com.example.demo.repository;

import com.example.demo.model.Member;
import java.util.List;

public interface MemberRepository {
    Member findById(String id);
    Member save(Member member);
    List<Member> findByCollectivityId(String collectivityId);
}