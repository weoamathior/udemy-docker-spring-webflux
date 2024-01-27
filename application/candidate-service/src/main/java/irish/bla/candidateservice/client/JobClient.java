package irish.bla.candidateservice.client;

import irish.bla.candidateservice.dto.JobDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Service
public class JobClient {
    private final WebClient client;
    public JobClient(@Value("${job.service.url}") String url) {
        this.client = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<List<JobDto>> getRecommendedJobs(Set<String> skills) {
        return this.client.get()
                .uri(u -> u.path("search").queryParam("skills", skills).build())
                .retrieve()
                .bodyToFlux(JobDto.class)
                .collectList();
    }

}
