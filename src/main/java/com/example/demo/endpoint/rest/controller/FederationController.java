package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.mapper.CollectivityRestMapper;
import com.example.demo.endpoint.rest.mapper.MemberRestMapper;
import com.example.demo.endpoint.rest.model.CollectivityRest;
import com.example.demo.endpoint.rest.model.CreateCollectivityRest;
import com.example.demo.endpoint.rest.model.CreateMemberRest;
import com.example.demo.endpoint.rest.model.MemberRest;
import com.example.demo.model.CreateCollectivity;
import com.example.demo.model.CreateMember;
import com.example.demo.service.CollectivityService;
import com.example.demo.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class FederationController {

    private final CollectivityService collectivityService;
    private final MemberService memberService;
    private final CollectivityRestMapper collectivityRestMapper;
    private final MemberRestMapper memberRestMapper;

    public FederationController(
            CollectivityService collectivityService,
            MemberService memberService,
            CollectivityRestMapper collectivityRestMapper,
            MemberRestMapper memberRestMapper
    ) {
        this.collectivityService = collectivityService;
        this.memberService = memberService;
        this.collectivityRestMapper = collectivityRestMapper;
        this.memberRestMapper = memberRestMapper;
    }

    @PostMapping("/collectivities")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CollectivityRest> createCollectivities(@RequestBody List<CreateCollectivityRest> request) {
        List<CreateCollectivity> inputs = new ArrayList<CreateCollectivity>();

        for (CreateCollectivityRest item : request) {
            inputs.add(collectivityRestMapper.toDomainCreate(item));
        }

        List<CollectivityRest> response = new ArrayList<CollectivityRest>();
        List<com.example.agriculture_federation.model.Collectivity> created = collectivityService.createAll(inputs);

        for (com.example.agriculture_federation.model.Collectivity collectivity : created) {
            response.add(collectivityRestMapper.toRest(collectivity));
        }

        return response;
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MemberRest> createMembers(@RequestBody List<CreateMemberRest> request) {
        List<CreateMember> inputs = new ArrayList<CreateMember>();

        for (CreateMemberRest item : request) {
            inputs.add(memberRestMapper.toDomainCreate(item));
        }

        List<MemberRest> response = new ArrayList<MemberRest>();
        List<com.example.agriculture_federation.model.Member> created = memberService.createAll(inputs);

        for (com.example.agriculture_federation.model.Member member : created) {
            response.add(memberRestMapper.toRest(member));
        }

        return response;
    }
}