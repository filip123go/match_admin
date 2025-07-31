package com.accepted.match_api.validators;

import com.accepted.match_api.dto.MatchDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifferentTeamsValidator implements ConstraintValidator<DifferentTeams, MatchDto> {

    @Override
    public boolean isValid(MatchDto dto, ConstraintValidatorContext context) {
        if (dto.getTeamA() == null || dto.getTeamB() == null) return true; // let @NotBlank handle nulls

        return !dto.getTeamA().equalsIgnoreCase(dto.getTeamB());
    }
}