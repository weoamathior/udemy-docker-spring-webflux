package irish.bla.jobservice.controller;

import irish.bla.jobservice.dto.JobDto;
import irish.bla.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("all")
    public Flux<JobDto> all() {
        return jobService.allJobs();
    }

    @GetMapping("search")
    public Flux<JobDto> search(@RequestParam Set<String> skills) {
        return jobService.jobsBySkills(skills);
    }

    @PostMapping
    public Mono<JobDto> save(@RequestBody Mono<JobDto> mono) {
        return jobService.save(mono);
    }

}
