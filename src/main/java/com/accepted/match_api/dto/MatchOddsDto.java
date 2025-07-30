package com.accepted.match_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchOddsDto {
    Long id;
    String specifier;
    BigDecimal odd;
    Long matchId;
}