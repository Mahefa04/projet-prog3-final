package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ActivityStatus;
import com.example.demo.model.Collectivity;
import com.example.demo.model.CollectivityInformation;
import com.example.demo.model.CreateMembershipFee;
import com.example.demo.model.MembershipFee;
import com.example.demo.repository.CollectivityRepository;
import com.example.demo.repository.MembershipFeeRepository;
import com.example.demo.service.validator.MembershipFeeValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MembershipFeeRepository membershipFeeRepository;
    private final MembershipFeeValidator membershipFeeValidator;

    public CollectivityService(
            CollectivityRepository collectivityRepository,
            MembershipFeeRepository membershipFeeRepository,
            MembershipFeeValidator membershipFeeValidator
    ) {
        this.collectivityRepository = collectivityRepository;
        this.membershipFeeRepository = membershipFeeRepository;
        this.membershipFeeValidator = membershipFeeValidator;
    }

    public Collectivity updateInformation(String id, CollectivityInformation info) {
        Collectivity existingCollectivity;
        Collectivity collectivityWithSameName;
        Collectivity collectivityWithSameNumber;

        existingCollectivity = collectivityRepository.findById(id);

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

        collectivityWithSameName = collectivityRepository.findByName(info.getName());
        if (collectivityWithSameName != null
                && !collectivityWithSameName.getId().equals(existingCollectivity.getId())) {
            throw new BadRequestException("Collectivity name is already used by another collectivity");
        }

        collectivityWithSameNumber = collectivityRepository.findByNumber(info.getNumber());
        if (collectivityWithSameNumber != null
                && !collectivityWithSameNumber.getId().equals(existingCollectivity.getId())) {
            throw new BadRequestException("Collectivity number is already used by another collectivity");
        }

        existingCollectivity.setName(info.getName());
        existingCollectivity.setNumber(info.getNumber());

        return collectivityRepository.save(existingCollectivity);
    }

    public List<MembershipFee> createFees(String id, List<CreateMembershipFee> fees) {
        Collectivity collectivity;
        List<MembershipFee> createdFees;
        MembershipFee membershipFee;
        MembershipFee saved;
        int i;

        collectivity = collectivityRepository.findById(id);

        if (collectivity == null) {
            throw new NotFoundException("Collectivity not found: " + id);
        }

        membershipFeeValidator.validateAll(fees);

        createdFees = new ArrayList<MembershipFee>();

        for (i = 0; i < fees.size(); i++) {
            membershipFee = new MembershipFee();
            membershipFee.setId("MF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            membershipFee.setEligibleFrom(fees.get(i).getEligibleFrom());
            membershipFee.setFrequency(fees.get(i).getFrequency());
            membershipFee.setAmount(fees.get(i).getAmount());
            membershipFee.setLabel(fees.get(i).getLabel());
            membershipFee.setStatus(ActivityStatus.ACTIVE);

            saved = membershipFeeRepository.save(id, membershipFee);
            createdFees.add(saved);
        }

        return createdFees;
    }

    public List<MembershipFee> getFees(String id) {
        Collectivity collectivity;

        collectivity = collectivityRepository.findById(id);

        if (collectivity == null) {
            throw new NotFoundException("Collectivity not found: " + id);
        }

        return membershipFeeRepository.findByCollectivityId(id);
    }
}