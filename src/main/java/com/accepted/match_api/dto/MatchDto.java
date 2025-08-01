package com.accepted.match_api.dto;

import com.accepted.match_api.enums.Sport;
import com.accepted.match_api.validators.DifferentTeams;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DifferentTeams
public class MatchDto {

    private Long id;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotNull(message = "Match date is required")
    @FutureOrPresent(message = "Match date must be today or in the future")
    @Schema(example = "YYYY-mm-dd ex: 2023-10-15")
    private LocalDate matchDate;

    @NotNull(message = "Match time is required")
    @Schema(example = "20:30:00")
    private LocalTime matchTime;

    @NotBlank(message = "Team A must not be blank")
    private String teamA;

    @NotBlank(message = "Team B must not be blank")
    private String teamB;

    @NotNull(message = "Sport must be specified")
    private Sport sport;

    @NotNull(message = "Odds list must not be null")
    @Size(min = 1, message = "At least one odds entry is required")
    private List<@Valid MatchOddsDto> odds;
}
