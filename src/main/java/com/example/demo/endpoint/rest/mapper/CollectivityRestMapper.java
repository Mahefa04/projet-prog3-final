package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CollectivityRest;
import com.example.demo.endpoint.rest.model.CollectivityStructureRest;
import com.example.demo.endpoint.rest.model.CreateCollectivityRest;
import com.example.demo.endpoint.rest.model.CreateCollectivityStructureRest;
import com.example.demo.model.Collectivity;
import com.example.demo.model.CollectivityStructure;
import com.example.demo.model.CreateCollectivity;
import com.example.demo.model.CreateCollectivityStructure;
import com.example.demo.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CollectivityRestMapper {

    private final MemberRestMapper memberRestMapper;

    public CollectivityRestMapper(MemberRestMapper memberRestMapper) {
        this.memberRestMapper = memberRestMapper;
    }

    public CreateCollectivity toDomainCreate(CreateCollectivityRest rest) {
        CreateCollectivityStructure structure = null;

        if (rest.getStructure() != null) {
            CreateCollectivityStructureRest s = rest.getStructure();
            structure = new CreateCollectivityStructure(
                    s.getPresident(),
                    s.getVicePresident(),
                    s.getTreasurer(),
                    s.getSecretary()
            );
        }

        return new CreateCollectivity(
                rest.getLocation(),
                rest.getMembers(),
                rest.getFederationApproval(),
                structure
        );
    }

    public CollectivityRest toRest(Collectivity domain) {
        List<com.example.demo.endpoint.rest.model.MemberRest> members = new ArrayList<com.example.demo.endpoint.rest.model.MemberRest>();

        for (Member member : domain.getMembers()) {
            members.add(memberRestMapper.toRestWithoutNestedReferees(member));
        }

        return new CollectivityRest(
                domain.getId(),
                domain.getLocation(),
                toRestStructure(domain.getStructure()),
                members
        );
    }

    private CollectivityStructureRest toRestStructure(CollectivityStructure structure) {
        return new CollectivityStructureRest(
                memberRestMapper.toRestWithoutNestedReferees(structure.getPresident()),
                memberRestMapper.toRestWithoutNestedReferees(structure.getVicePresident()),
                memberRestMapper.toRestWithoutNestedReferees(structure.getTreasurer()),
                memberRestMapper.toRestWithoutNestedReferees(structure.getSecretary())
        );
    }
}