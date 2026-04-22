package com.example.demo.repository;

import com.example.demo.model.Collectivity;

public interface CollectivityRepository {
    Collectivity findById(String id);
    Collectivity findByName(String name);
    Collectivity findByNumber(Integer number);
    Collectivity save(Collectivity collectivity);
}