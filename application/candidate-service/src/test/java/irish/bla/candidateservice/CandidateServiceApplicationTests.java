package irish.bla.candidateservice;

import irish.bla.candidateservice.repository.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@EnableAutoConfiguration(exclude = MongoReactiveAutoConfiguration.class)
@MockBean({
	CandidateRepository.class
})
class CandidateServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
