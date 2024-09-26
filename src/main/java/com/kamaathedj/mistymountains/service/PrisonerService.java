package com.kamaathedj.mistymountains.service;

import com.kamaathedj.mistymountains.model.Prisoner;
import com.kamaathedj.mistymountains.repository.PrisonerRepository;
import com.kamaathedj.mistymountains.repository.PrisonerStuffRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PrisonerService {
    private final PrisonerRepository prisonerRepository;
    private final PrisonerStuffRepository prisonerStuffRepository;

    public PrisonerService(PrisonerRepository prisonerRepository, PrisonerStuffRepository prisonerStuffRepository) {
        this.prisonerRepository = prisonerRepository;
        this.prisonerStuffRepository = prisonerStuffRepository;
    }
    @PreAuthorize("hasRole('USER')")
   // @PostAuthorize("returnObject?.body)
    public Flux<Prisoner> getPrisonersService() {
        return prisonerRepository.findAll();
    }

    //testing purpose
    @PreAuthorize("hasRole('USER')")
    public Flux<Prisoner> getPrisonerFlux(){
        return prisonerRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Prisoner> addPrisonerService(Prisoner prisoner) {
        //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        return prisonerRepository.save(prisoner);
    }
}
