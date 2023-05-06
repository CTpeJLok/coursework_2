package com.sport_objects.repositories;

import com.sport_objects.entities.EventDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDistributionRepository extends JpaRepository<EventDistribution, Long> {

    List<EventDistribution> findByEventId(Long id);

    List<EventDistribution> findByTeamId(Long id);

    List<EventDistribution> findByUserId(Long id);

    List<EventDistribution> findByPositionId(Long id);

}
