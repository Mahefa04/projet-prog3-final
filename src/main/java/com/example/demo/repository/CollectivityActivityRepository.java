package com.example.demo.repository;

import com.example.demo.model.CollectivityActivity;

import java.util.List;

public interface CollectivityActivityRepository {
    boolean collectivityExists(String collectivityId);

    CollectivityActivity save(String collectivityId, CollectivityActivity activity);

    List<CollectivityActivity> findByCollectivityId(String collectivityId);
}