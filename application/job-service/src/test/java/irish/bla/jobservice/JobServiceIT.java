package irish.bla.jobservice;

import irish.bla.jobservice.compose.ComposeBaseTest;
import irish.bla.jobservice.dto.JobDto;
import irish.bla.jobservice.generic.BaseTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceIT extends ComposeBaseTest {

	@Autowired
	private WebTestClient client;

	@Test
	void allJobsTest() {
		this.client.get()
				.uri("/job/all")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$").isNotEmpty();
	}
	@Test
	void searchBySkillsTest() {
		this.client.get()
				.uri("/job/search?skills=java")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.size()").isEqualTo(3);
	}
	@Test
	void postJobTest() {
		JobDto dto = JobDto.builder()
				.description("k8s engineer")
				.company("Google")
				.skills(Set.of("k8s"))
				.salary(200000)
				.remote(true).build();

		this.client.post()
				.uri("/job")
				.bodyValue(dto)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.id").isNotEmpty();
	}

}
