package com.test.rps.metrics.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayRepository extends CrudRepository<Play, Long> {
    @Query("SELECT new com.test.rps.metrics.model.PlayCount(p.player, p.computer, COUNT(p.id)) "
            + "FROM Play AS p GROUP BY p.player, p.computer")
    public List<PlayCount> countTotalsByPlayerAndComputer();
}
