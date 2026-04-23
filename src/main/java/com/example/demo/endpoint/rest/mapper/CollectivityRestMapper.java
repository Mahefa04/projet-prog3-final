package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CollectivityRest;
import com.example.demo.model.Collectivity;
import org.springframework.stereotype.Component;

@Component
public class CollectivityRestMapper {

    public CollectivityRest toRest(Collectivity domain) {
        CollectivityRest rest = new CollectivityRest();

        rest.setId(domain.getId());
        rest.setNumber(domain.getNumber());
        rest.setName(domain.getName());
        rest.setLocality(domain.getLocality());
        rest.setSpecialization(domain.getSpecialization());

        return rest;
    }
}