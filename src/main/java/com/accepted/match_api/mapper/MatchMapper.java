package com.accepted.match_api.mapper;

import com.accepted.match_api.dto.MatchDto;
import com.accepted.match_api.dto.MatchOddsDto;
import com.accepted.match_api.entity.Match;
import com.accepted.match_api.entity.MatchOdds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class MatchMapper {

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
                .odds(mapMatchOdds(match))
                .build();
    }

    private List<MatchOddsDto> mapMatchOdds(Match match) {
        if (match.getOdds() == null) return null;

        return match.getOdds().stream()
                .map(odds -> MatchOddsDto.builder()
                        .id(odds.getId())
                        .specifier(odds.getSpecifier())
                        .odd(odds.getOdd())
                        .matchId(match.getId())
                        .build())
                .collect(Collectors.toList());
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
            List<MatchOdds> odds = dto.getOdds().stream()
                    .map(oddsDto -> MatchOdds.builder()
                            .id(oddsDto.getId())
                            .specifier(oddsDto.getSpecifier())
                            .odd(oddsDto.getOdd())
                            .match(match)
                            .build())
                    .collect(Collectors.toList());

            match.setOdds(odds);
        }

        return match;
    }
}
