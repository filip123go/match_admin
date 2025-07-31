package com.accepted.match_api.controller;

import com.accepted.match_api.dto.MatchDto;
import com.accepted.match_api.service.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchControllerTest {

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchController matchController;

    private MatchDto matchDto1;
    private MatchDto matchDto2;

    @BeforeEach
    void setUp() {
        matchDto1 = MatchDto.builder()
                .id(1L)
                .build();

        matchDto2 = MatchDto.builder()
                .id(2L)
                .build();
    }

    @Test
    void getAllMatches_ReturnsListOfMatches() {
        List<MatchDto> expectedMatches = Arrays.asList(matchDto1, matchDto2);
        when(matchService.findAll()).thenReturn(expectedMatches);

        ResponseEntity<List<MatchDto>> response = matchController.getAllMatches();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(expectedMatches, response.getBody());
        verify(matchService, times(1)).findAll();
    }

    @Test
    void getAllMatches_ReturnsEmptyList_WhenNoMatchesExist() {
        when(matchService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<MatchDto>> response = matchController.getAllMatches();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
        verify(matchService, times(1)).findAll();
    }

    @Test
    void getMatchById_ReturnsMatch_WhenMatchExists() {
        Long matchId = 1L;
        when(matchService.findById(matchId)).thenReturn(matchDto1);

        ResponseEntity<MatchDto> response = matchController.getMatchById(matchId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(matchDto1, response.getBody());
        assertEquals(matchId, response.getBody().getId());
        verify(matchService, times(1)).findById(matchId);
    }

    @Test
    void createMatch_ReturnsCreatedMatch() {
        MatchDto inputDto = MatchDto.builder().build();
        MatchDto createdDto = MatchDto.builder().id(3L).build();
        when(matchService.create(inputDto)).thenReturn(createdDto);

        ResponseEntity<MatchDto> response = matchController.createMatch(inputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdDto, response.getBody());
        assertEquals(3L, response.getBody().getId());
        verify(matchService, times(1)).create(inputDto);
    }

    @Test
    void updateMatch_ReturnsUpdatedMatch() {
        Long matchId = 1L;
        MatchDto inputDto = MatchDto.builder().id(matchId).build();
        MatchDto updatedDto = MatchDto.builder().id(matchId).build();
        when(matchService.update(matchId, inputDto)).thenReturn(updatedDto);

        ResponseEntity<MatchDto> response = matchController.updateMatch(matchId, inputDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedDto, response.getBody());
        assertEquals(matchId, response.getBody().getId());
        verify(matchService, times(1)).update(matchId, inputDto);
    }

    @Test
    void deleteMatch_ReturnsNoContent() {
        Long matchId = 1L;
        doNothing().when(matchService).delete(matchId);

        ResponseEntity<Void> response = matchController.deleteMatch(matchId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response);
        verify(matchService, times(1)).delete(matchId);
    }
}