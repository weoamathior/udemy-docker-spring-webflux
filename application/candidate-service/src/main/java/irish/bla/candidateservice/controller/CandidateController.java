package irish.bla.candidateservice.controller;

import irish.bla.candidateservice.dto.CandidateDto;
import irish.bla.candidateservice.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
}
