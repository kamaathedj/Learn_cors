package com.kamaathedj.mistymountains.repository;

import com.kamaathedj.mistymountains.model.Belongings;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrisonerStuffRepository extends ReactiveCrudRepository<Belongings,Long> {
}
