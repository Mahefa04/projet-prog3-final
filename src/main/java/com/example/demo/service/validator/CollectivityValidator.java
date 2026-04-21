package com.example.demo.service.validator;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.CreateCollectivity;
import org.springframework.stereotype.Component;

@Component
public class CollectivityValidator {

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
}