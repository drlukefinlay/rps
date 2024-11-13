package com.test.rps.metrics.model.dto;

import java.time.Instant;

import com.test.rps.game.model.Choice;
import com.test.rps.metrics.model.Play;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayDTO {
    private long id;
    private Instant timestamp;
    private Choice player;
    private Choice computer;
    
    public PlayDTO(Play play) {
        this.id = play.getId();
        this.timestamp = play.getTimestamp();
        this.player = play.getPlayer();
        this.computer = play.getComputer();
    }
}
