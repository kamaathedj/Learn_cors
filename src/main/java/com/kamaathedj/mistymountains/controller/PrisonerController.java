package com.kamaathedj.mistymountains.controller;

import com.kamaathedj.mistymountains.model.Prisoner;
import com.kamaathedj.mistymountains.service.PrisonerService;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
//@CrossOrigin(value = {"http://127.0.0.1:3000"},maxAge = 9000)
@RequestMapping(value = "api/v1")
public class PrisonerController {
    private final PrisonerService prisonerService;

    public PrisonerController(PrisonerService prisonerService) {
        this.prisonerService = prisonerService;
    }
    @ExceptionHandler
    ResponseEntity<?> error(Exception e){
        System.out.println(NestedExceptionUtils.getMostSpecificCause(e));
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/prisoners")
   public  ResponseEntity<Flux<Prisoner>> getPrisoners(Authentication auth) throws URISyntaxException {
        System.out.println(auth.getAuthorities());
        return ResponseEntity.ok(prisonerService.getPrisonersService());
    }
    @GetMapping("/about")
    @PostAuthorize("returnObject?.body.equals('about')")
    public ResponseEntity<String> About() {
        return ResponseEntity.of(Optional.of("about"));
    }

    @PostMapping("/prisoner")
    public ResponseEntity<Mono<Prisoner>> addPrisoner(@RequestBody Prisoner prisoner) throws URISyntaxException {
        Assert.isTrue(Character.isUpperCase(prisoner.getName().charAt(0)),()-> "Name must start with upper case");
        if(prisoner.getPrisonedWhen() == LocalDate.now()){
            System.out.println("got caught today");
        }
        URI location = new URI("http://localhost:9090/prisoner");
        return ResponseEntity.created(location).body(prisonerService.addPrisonerService(prisoner));

    }

}
