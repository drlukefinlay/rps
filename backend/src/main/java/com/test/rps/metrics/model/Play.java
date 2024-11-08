package com.test.rps.metrics.model;

import java.time.Instant;

import com.test.rps.game.model.Choice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a completed play
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Play {

    public Play(Choice player, Choice computer) {
        this.player = player;
        this.computer = computer;
        this.timestamp = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Instant timestamp;

    private Choice player;

    private Choice computer;
}
