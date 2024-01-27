package irish.bla.candidateservice;

import irish.bla.candidateservice.dto.CandidateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
public class CandidateServiceIT extends BaseTest{
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnAll() {
        this.webTestClient.get()
                .uri("/candidate/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void shouldReturnById() {
        this.webTestClient.get()
                .uri("/candidate/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);
    }

    @Test
    void shouldCreate() {
        CandidateDto theValue = new CandidateDto();
        theValue.setName("Chuck");
        theValue.setSkills(Set.of("bullshitting","ladder climbing"));
        this.webTestClient.post()
                .uri("/candidate")
                .bodyValue(theValue)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Chuck");
    }
}


