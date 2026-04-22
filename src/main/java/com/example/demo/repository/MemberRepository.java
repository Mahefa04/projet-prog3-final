package com.example.demo.repository;

import com.example.demo.model.Member;

public interface MemberRepository {
    Member findById(String id);
    Member save(Member member);
}