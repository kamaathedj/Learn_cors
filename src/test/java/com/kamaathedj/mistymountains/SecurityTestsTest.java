package com.kamaathedj.mistymountains;

import com.kamaathedj.mistymountains.model.Prisoner;
import com.kamaathedj.mistymountains.service.PrisonerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@SpringBootTest
class SecurityTestsTest {
    @Autowired
    private PrisonerService service;

    @Test
    void testCannotAccess() {
        StepVerifier.create(this.service.getPrisonersService()).
                expectError(AccessDeniedException.class).verify();
    }

    @Test
    @WithMockUser
    void testWithMockUser() {
        StepVerifier.create(this.service.getPrisonersService()).
                expectError(AccessDeniedException.class).verify();
    }

    @Test
    @WithMockUser(roles = "USER", username = "meme",password = "meme")
    void testDiffrentRoleThanExpected() {
        StepVerifier.create(this.service.getPrisonersService()).
                expectError(AccessDeniedException.class).verify();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testWithoutUsernameAndPassword() {
        StepVerifier.create(this.service.getPrisonersService()).
                expectError(AccessDeniedException.class).verify();
    }



    @Test
    @WithMockUser(roles = "ADMIN", username = "memes",password = "memes")
    void testWithMockUserWithRoleAdmin(){
        StepVerifier.create(this.service.getPrisonersService()).
                expectNextCount(3).verifyComplete();

    }

    @Test
    @WithMockUser(roles = "ADMIN", username = "meme",password = "meme")
    void testAddingPrisoner() {
        Prisoner p = new Prisoner("test","just testing the fence", LocalDate.now());
        StepVerifier.create(this.service.addPrisonerService(p))
                .expectNext(p);
    }


}