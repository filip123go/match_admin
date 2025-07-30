package com.accepted.match_api.dto;

import com.accepted.match_api.enums.Sport;
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
public class MatchDto {
    Long id;
    String description;
    LocalDate matchDate;
    LocalTime matchTime;
    String teamA;
    String teamB;
    Sport sport;
    List<MatchOddsDto> odds;
}
