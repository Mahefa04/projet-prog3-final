package com.example.demo.endpoint.rest.controller;



import com.example.demo.model.Collectivity;
import com.example.demo.service.CollectivityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private final CollectivityService collectivityService;

    public CollectivityController(CollectivityService collectivityService) {
        this.collectivityService = collectivityService;
    }

    @GetMapping("/{id}")
    public Collectivity getCollectivityById(@PathVariable String id) {
        return collectivityService.findById(id);
    }
}
