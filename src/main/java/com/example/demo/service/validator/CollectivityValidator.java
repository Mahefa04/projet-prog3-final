package com.example.demo.service.validator;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.CreateCollectivity;
import com.example.demo.repository.CollectivityRepository;
import org.springframework.stereotype.Component;

@Component
public class CollectivityValidator {

    CollectivityRepository collectivityRepository;

    public void validate(CreateCollectivity input) {
        validateFederationApproval(input);
        validateStructure(input);
    }

    private void validateFederationApproval(CreateCollectivity input) {
        if (input.getFederationApproval() == null || !input.getFederationApproval()) {
            throw new BadRequestException("Collectivity without federation approval");
        }
    }

    private void validateStructure(CreateCollectivity input) {
        if (input.getStructure() == null) {
            throw new BadRequestException("Collectivity structure is missing");
        }

        if (input.getStructure().getPresidentId() == null
                || input.getStructure().getVicePresidentId() == null
                || input.getStructure().getTreasurerId() == null
                || input.getStructure().getSecretaryId() == null) {
            throw new BadRequestException("Collectivity structure is incomplete");
        }
    }

    public void validateUniqueNumberAndName(String number, String name) {
        if (collectivityRepository.existsByNumber(number)) {
            throw new BadRequestException("Collectivity number already exists");
        }

        if (collectivityRepository.existsByName(name)) {
            throw new BadRequestException("Collectivity name already exists");
        }
    }
}