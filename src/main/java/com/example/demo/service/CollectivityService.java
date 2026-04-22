package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Collectivity;
import com.example.demo.model.CollectivityInformation;
import com.example.demo.model.CreateCollectivity;
import com.example.demo.model.Member;
import com.example.demo.repository.CollectivityRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.factory.CollectivityFactory;
import com.example.demo.service.validator.CollectivityValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;
    private final CollectivityValidator collectivityValidator;
    private final CollectivityFactory collectivityFactory;
    private final Collectivity collectivity;

    public CollectivityService(
            CollectivityRepository collectivityRepository,
            MemberRepository memberRepository,
            CollectivityValidator collectivityValidator,
            CollectivityFactory collectivityFactory,
            Collectivity collectivity
    ) {
        this.collectivityRepository = collectivityRepository;
        this.memberRepository = memberRepository;
        this.collectivityValidator = collectivityValidator;
        this.collectivityFactory = collectivityFactory;
        this.collectivity = collectivity;
    }

    public List<Collectivity> createAll(List<CreateCollectivity> inputs) {
        List<Collectivity> results = new ArrayList<Collectivity>();

        for (CreateCollectivity input : inputs) {
            collectivityValidator.validate(input);

            collectivityValidator.validateUniqueNumberAndName(
                    collectivity.getNumber(),
                    collectivity.getName()
            );

            List<Member> members = new ArrayList<Member>();
            if (input.getMemberIds() != null) {
                for (String memberId : input.getMemberIds()) {
                    members.add(findMemberByIdOrThrow(memberId));
                }
            }

            Member president = findMemberByIdOrThrow(input.getStructure().getPresidentId());
            Member vicePresident = findMemberByIdOrThrow(input.getStructure().getVicePresidentId());
            Member treasurer = findMemberByIdOrThrow(input.getStructure().getTreasurerId());
            Member secretary = findMemberByIdOrThrow(input.getStructure().getSecretaryId());

            Collectivity collectivity = collectivityFactory.create(
                    input,
                    members,
                    president,
                    vicePresident,
                    treasurer,
                    secretary
            );

            Collectivity saved = collectivityRepository.save(collectivity);
            results.add(saved);
        }

        return results;
    }

    private Member findMemberByIdOrThrow(String memberId) {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new NotFoundException("Member not found: " + memberId);
        }
        return member;
    }

    public Collectivity updateInformation(String id, CollectivityInformation info) {
        Collectivity existingCollectivity = collectivityRepository.findById(id);

        if (existingCollectivity == null) {
            throw new NotFoundException("Collectivity not found: " + id);
        }

        if (info == null) {
            throw new BadRequestException("Collectivity information is required");
        }

        if (info.getName() == null || info.getName().trim().isEmpty()) {
            throw new BadRequestException("Collectivity name is required");
        }

        if (info.getNumber() == null) {
            throw new BadRequestException("Collectivity number is required");
        }

        if (existingCollectivity.getName() != null
                && !existingCollectivity.getName().equals(info.getName())) {
            throw new BadRequestException("Collectivity name cannot be changed once assigned");
        }

        if (existingCollectivity.getNumber() != null
                && !existingCollectivity.getNumber().equals(info.getNumber())) {
            throw new BadRequestException("Collectivity number cannot be changed once assigned");
        }

        Collectivity collectivityWithSameName = collectivityRepository.findByName(info.getName());
        if (collectivityWithSameName != null
                && !collectivityWithSameName.getId().equals(existingCollectivity.getId())) {
            throw new BadRequestException("Collectivity name is already used by another collectivity");
        }

        Collectivity collectivityWithSameNumber = collectivityRepository.findByNumber(info.getNumber());
        if (collectivityWithSameNumber != null
                && !collectivityWithSameNumber.getId().equals(existingCollectivity.getId())) {
            throw new BadRequestException("Collectivity number is already used by another collectivity");
        }

        existingCollectivity.setName(info.getName());
        existingCollectivity.setNumber(info.getNumber());

        return collectivityRepository.save(existingCollectivity);
    }
}