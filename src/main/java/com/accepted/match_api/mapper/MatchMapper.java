package com.accepted.match_api.mapper;

import com.accepted.match_api.dto.MatchDto;
import com.accepted.match_api.entity.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    @Autowired
    private MatchOddsMapper matchOddsMapper;

    public MatchDto toDto(Match match) {
        if (match == null) return null;

        return MatchDto.builder()
                .id(match.getId())
                .description(match.getDescription())
                .matchDate(match.getMatchDate())
                .matchTime(match.getMatchTime())
                .teamA(match.getTeamA())
                .teamB(match.getTeamB())
                .sport(match.getSport())
                .odds(matchOddsMapper.toDtoList(match.getOdds()))
                .build();
    }

    public Match toEntity(MatchDto dto) {
        if (dto == null) return null;

        Match match = Match.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .matchDate(dto.getMatchDate())
                .matchTime(dto.getMatchTime())
                .teamA(dto.getTeamA())
                .teamB(dto.getTeamB())
                .sport(dto.getSport())
                .build();

        if (dto.getOdds() != null) {
            match.setOdds(matchOddsMapper.toEntityList(dto.getOdds(), match));
        }

        return match;
    }
}