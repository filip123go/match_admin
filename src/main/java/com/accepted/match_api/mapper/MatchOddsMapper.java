package com.accepted.match_api.mapper;


import com.accepted.match_api.dto.MatchOddsDto;
import com.accepted.match_api.entity.Match;
import com.accepted.match_api.entity.MatchOdds;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchOddsMapper {

    default MatchOddsDto mapSingleOdd(MatchOdds odds, Match match) {
        if (odds == null) return null;

        return MatchOddsDto.builder()
                .id(odds.getId())
                .specifier(odds.getSpecifier())
                .odd(odds.getOdd())
                .matchId(match.getId())
                .build();
    }


    MatchOdds toEntity(MatchOddsDto dto, @Context Match match);
}
