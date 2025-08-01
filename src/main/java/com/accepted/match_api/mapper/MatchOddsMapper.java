package com.accepted.match_api.mapper;

import com.accepted.match_api.dto.MatchOddsDto;
import com.accepted.match_api.entity.Match;
import com.accepted.match_api.entity.MatchOdds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchOddsMapper {

    public List<MatchOddsDto> toDtoList(List<MatchOdds> odds) {
        if (odds == null) return null;

        return odds.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MatchOddsDto toDto(MatchOdds odds) {
        if (odds == null) return null;

        return MatchOddsDto.builder()
                .id(odds.getId())
                .specifier(odds.getSpecifier())
                .odd(odds.getOdd())
                .matchId(odds.getMatch().getId())
                .build();
    }

    public List<MatchOdds> toEntityList(List<MatchOddsDto> oddsDto, Match match) {
        if (oddsDto == null) return null;

        return oddsDto.stream()
                .map(dto -> toEntity(dto, match))
                .collect(Collectors.toList());
    }

    public MatchOdds toEntity(MatchOddsDto dto, Match match) {
        if (dto == null) return null;

        return MatchOdds.builder()
                .id(dto.getId())
                .specifier(dto.getSpecifier())
                .odd(dto.getOdd())
                .match(match)
                .build();
    }
}