package com.accepted.match_api.service;


import com.accepted.match_api.dto.MatchDto;
import com.accepted.match_api.entity.Match;
import com.accepted.match_api.mapper.MatchMapper;
import com.accepted.match_api.repository.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    public List<MatchDto> findAll() {
        return matchRepository.findAll().stream()
                .map(matchMapper::toDto)
                .toList();
    }
    public Page<MatchDto> getAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable).map(matchMapper::toDto);
    }

    public MatchDto findById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Match not found"));
        return matchMapper.toDto(match);
    }

    public MatchDto create(MatchDto dto) {
        Match match = matchMapper.toEntity(dto);
        return matchMapper.toDto(matchRepository.save(match));
    }

    public MatchDto update(Long id, MatchDto dto) {
        Match existing = matchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Match not found"));

        Match updated = matchMapper.toEntity(dto);
        updated.setId(existing.getId());
        return matchMapper.toDto(matchRepository.save(updated));
    }

    public void delete(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Match not found"));
        matchRepository.delete(match);
    }
}
