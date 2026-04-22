package com.example.demo.repository;

import com.example.demo.model.Collectivity;

public interface CollectivityRepository {
    Collectivity findById(String id);
    Collectivity save(Collectivity collectivity);
}