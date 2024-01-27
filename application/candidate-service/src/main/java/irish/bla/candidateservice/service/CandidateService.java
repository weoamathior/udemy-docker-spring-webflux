package irish.bla.candidateservice.service;

import irish.bla.candidateservice.client.JobClient;
import irish.bla.candidateservice.dto.CandidateDetailsDto;
import irish.bla.candidateservice.dto.CandidateDto;
import irish.bla.candidateservice.repository.CandidateRepository;
import irish.bla.candidateservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final JobClient jobClient;

    public Flux<CandidateDto> allCandidates() {
        return candidateRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CandidateDetailsDto> findById(String id) {
        return candidateRepository.findById(id)
                .map(EntityDtoUtil::toDetailsDto)
                .flatMap(this::addRecommendedJobs);
    }

    private Mono<CandidateDetailsDto> addRecommendedJobs(CandidateDetailsDto dto) {
            return jobClient.getRecommendedJobs(dto.getSkills())
                    .doOnNext(dto::setRecommendedJobs)
                    .thenReturn(dto);
    }
    public Mono<CandidateDto> create(Mono<CandidateDto> dto) {
        return dto.map(EntityDtoUtil::toEntity)
                .flatMap(candidateRepository::save)
                .map(EntityDtoUtil::toDto);
    }
}
