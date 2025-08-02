package com.accepted.match_api.controller;

import com.accepted.match_api.dto.MatchDto;
import com.accepted.match_api.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "*") //TODO: THIS IS ONLY FOR DEVELOPMENT, IN PRODUCTION ADD THE ACTUAL FRONTEND URL
@Validated
@Tag(name = "Match Controller", description = "Manage sports matches")
public class MatchController {

    private final MatchService matchService;

    private static final String MATCH_EXAMPLE_JSON = """
{
  "description": "This is the description",
  "matchDate": "2026-10-15",
  "matchTime": "20:30:00",
  "teamA": "TEAM 1",
  "teamB": "TEAM 2",
  "sport": "FOOTBALL | BASKETBALL",
      "odds": [
        {
          "specifier": "1",
          "odd": 1.75
        },
        {
          "specifier": "X",
          "odd": 3.2
        },
        {
          "specifier": "2",
          "odd": 4.1
        }
      ]
    }
""";

    private static final String UPDATE_MATCH_EXAMPLE = """
    {
      "description": "OSFP - PAO",
      "matchDate": "2026-08-01",
      "matchTime": "20:30:00",
      "teamA": "Staros",
      "teamB": "PAO",
      "sport": "FOOTBALL",
      "odds": [
        {
          "specifier": "1",
          "odd": 1.75
        },
        {
          "specifier": "X",
          "odd": 3.2
        },
        {
          "specifier": "2",
          "odd": 4.1
        }
      ]
    }
""";
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    @Operation(summary = "Get all matches")
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        return ResponseEntity.ok(matchService.findAll());
    }

    @GetMapping("/matches-paginated")
    @Operation(summary = "Get all matches with pagination")
    public Page<MatchDto> getMatchesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return matchService.getAllMatches(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a match by ID")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Create a new match",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    value = MATCH_EXAMPLE_JSON
                            )
                    )
            )
    )
    public ResponseEntity<MatchDto> createMatch(@Valid @RequestBody MatchDto dto) {
        return ResponseEntity.ok(matchService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing match",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    value = UPDATE_MATCH_EXAMPLE
                            )
                    )
            )
    )
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
