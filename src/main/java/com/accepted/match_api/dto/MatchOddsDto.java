package com.accepted.match_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    private Long id;

    @NotBlank(message = "Specifier is required")
    @Schema(example = "1 OR X OR 2")
    @Pattern(regexp = "1|X|2", message = "Specifier must be one of: 1, X, 2")
    private String specifier;
    @NotNull(message = "Odd is required")
    private BigDecimal odd;

    private Long matchId;
}