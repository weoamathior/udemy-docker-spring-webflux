package irish.bla.jobservice.service;

import irish.bla.jobservice.dto.JobDto;
import irish.bla.jobservice.repository.JobRepository;
import irish.bla.jobservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Flux<JobDto> allJobs() {
        return jobRepository
                .findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Flux<JobDto> jobsBySkills(Set<String> skills) {
        return jobRepository
                .findBySkillsIn(skills)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<JobDto> save(Mono<JobDto> mono) {
        return mono.map(EntityDtoUtil::toEntity)
                .flatMap(jobRepository::save)
                .map(EntityDtoUtil::toDto);

    }

}
