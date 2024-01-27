package irish.bla.candidateservice.service;

import irish.bla.candidateservice.dto.CandidateDto;
import irish.bla.candidateservice.repository.CandidateRepository;
import irish.bla.candidateservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public Flux<CandidateDto> allCandidates() {
        return candidateRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CandidateDto> findById(String id) {
        return candidateRepository.findById(id).map(EntityDtoUtil::toDto);
    }

}
