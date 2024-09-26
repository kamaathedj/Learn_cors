package com.kamaathedj.mistymountains.repository;

import com.kamaathedj.mistymountains.model.Prisoner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrisonerRepository extends ReactiveCrudRepository<Prisoner,Long> {
}
