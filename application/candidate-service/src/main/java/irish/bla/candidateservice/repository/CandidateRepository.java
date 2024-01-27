package irish.bla.candidateservice.repository;

import irish.bla.candidateservice.entity.Candidate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CandidateRepository extends ReactiveCrudRepository<Candidate,String> {
}
