package com.accepted.match_api.controller;

import com.accepted.match_api.dto.MatchDto;
import com.accepted.match_api.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@Validated
@Tag(name = "Match Controller", description = "Manage sports matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    @Operation(summary = "Get all matches")
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        return ResponseEntity.ok(matchService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a match by ID")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new match")
    public ResponseEntity<MatchDto> createMatch(@Valid @RequestBody MatchDto dto) {
        return ResponseEntity.ok(matchService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing match")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable Long id, @Valid @RequestBody MatchDto dto) {
        return ResponseEntity.ok(matchService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a match by ID")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
