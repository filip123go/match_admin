package com.accepted.match_api.service;

import com.accepted.match_api.dto.MatchDto;
import com.accepted.match_api.entity.Match;
import com.accepted.match_api.mapper.MatchMapper;
import com.accepted.match_api.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private MatchMapper matchMapper;

    @InjectMocks
    private MatchService matchService;

    private Match match;
    private MatchDto matchDto;
    private final Long matchId = 1L;

    @BeforeEach
    void setUp() {
        match = new Match();
        match.setId(matchId);

        matchDto = new MatchDto();
    }

    @Test
    void findAll_ShouldReturnAllMatchesAsDtos() {
        Match match1 = new Match();
        Match match2 = new Match();
        List<Match> matches = List.of(match1, match2);

        MatchDto dto1 = new MatchDto();
        MatchDto dto2 = new MatchDto();

        when(matchRepository.findAll()).thenReturn(matches);
        when(matchMapper.toDto(match1)).thenReturn(dto1);
        when(matchMapper.toDto(match2)).thenReturn(dto2);

        List<MatchDto> result = matchService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));

        verify(matchRepository, times(1)).findAll();
        verify(matchMapper, times(1)).toDto(match1);
        verify(matchMapper, times(1)).toDto(match2);
    }

    @Test
    void findById_WhenMatchExists_ShouldReturnMatchDto() {
        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));
        when(matchMapper.toDto(match)).thenReturn(matchDto);

        MatchDto result = matchService.findById(matchId);

        assertNotNull(result);
        assertEquals(matchDto, result);
        verify(matchRepository).findById(matchId);
        verify(matchMapper).toDto(match);
    }

    @Test
    void findById_WhenMatchDoesNotExist_ShouldThrowException() {
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> matchService.findById(matchId));

        assertEquals(NOT_FOUND, exception.getStatusCode());
        assertEquals("Match not found", exception.getReason());
        verify(matchRepository).findById(matchId);
        verifyNoInteractions(matchMapper);
    }

    @Test
    void create_ShouldSaveAndReturnMatchDto() {
        when(matchMapper.toEntity(matchDto)).thenReturn(match);
        when(matchRepository.save(match)).thenReturn(match);
        when(matchMapper.toDto(match)).thenReturn(matchDto);

        MatchDto result = matchService.create(matchDto);

        assertNotNull(result);
        assertEquals(matchDto, result);
        verify(matchMapper).toEntity(matchDto);
        verify(matchRepository).save(match);
        verify(matchMapper).toDto(match);
    }

    @Test
    void update_WhenMatchExists_ShouldUpdateAndReturnMatchDto() {
        Match updatedMatch = new Match();
        updatedMatch.setId(matchId);

        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));
        when(matchMapper.toEntity(matchDto)).thenReturn(updatedMatch);
        when(matchRepository.save(updatedMatch)).thenReturn(updatedMatch);
        when(matchMapper.toDto(updatedMatch)).thenReturn(matchDto);

        MatchDto result = matchService.update(matchId, matchDto);

        assertNotNull(result);
        assertEquals(matchDto, result);
        assertEquals(matchId, updatedMatch.getId());
        verify(matchRepository).findById(matchId);
        verify(matchMapper).toEntity(matchDto);
        verify(matchRepository).save(updatedMatch);
        verify(matchMapper).toDto(updatedMatch);
    }

    @Test
    void update_WhenMatchDoesNotExist_ShouldThrowException() {
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> matchService.update(matchId, matchDto));

        assertEquals(NOT_FOUND, exception.getStatusCode());
        assertEquals("Match not found", exception.getReason());
        verify(matchRepository).findById(matchId);
        verifyNoInteractions(matchMapper);
    }

    @Test
    void delete_WhenMatchExists_ShouldDeleteMatch() {
        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));
        doNothing().when(matchRepository).delete(match);

        matchService.delete(matchId);

        verify(matchRepository).findById(matchId);
        verify(matchRepository).delete(match);
    }

    @Test
    void delete_WhenMatchDoesNotExist_ShouldThrowException() {
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> matchService.delete(matchId));

        assertEquals(NOT_FOUND, exception.getStatusCode());
        assertEquals("Match not found", exception.getReason());
        verify(matchRepository).findById(matchId);
        verify(matchRepository, never()).delete(any());
    }
}