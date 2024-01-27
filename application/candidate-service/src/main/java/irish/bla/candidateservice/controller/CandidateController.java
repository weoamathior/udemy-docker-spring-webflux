package irish.bla.candidateservice.controller;

import irish.bla.candidateservice.dto.CandidateDto;
import irish.bla.candidateservice.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping("all")
    public Flux<CandidateDto> all() {
        return candidateService.allCandidates();
    }

    @GetMapping("{candidateId}")
    public Mono<CandidateDto> findById(@PathVariable String candidateId) {
        return candidateService.findById(candidateId);
    }

    @PostMapping
    public Mono<CandidateDto> create(@RequestBody Mono<CandidateDto> dto) {
        return candidateService.create(dto);
    }
}
