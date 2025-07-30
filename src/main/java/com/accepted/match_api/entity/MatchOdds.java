package com.accepted.match_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "match_odds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specifier;

    private BigDecimal odd;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    @JsonBackReference
    private Match match;
}
