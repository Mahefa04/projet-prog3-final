package com.example.demo.service.factory;

import com.example.demo.model.Collectivity;
import com.example.demo.model.CollectivityStructure;
import com.example.demo.model.CreateCollectivity;
import com.example.demo.model.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectivityFactory {

    public Collectivity create(
            CreateCollectivity input,
            List<Member> members,
            Member president,
            Member vicePresident,
            Member treasurer,
            Member secretary
    ) {
        CollectivityStructure structure = new CollectivityStructure(
                president,
                vicePresident,
                treasurer,
                secretary
        );

        return new Collectivity(
                null,
                input.getLocation(),
                structure,
                members
        );
    }
}