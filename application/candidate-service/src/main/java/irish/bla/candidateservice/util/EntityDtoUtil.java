package irish.bla.candidateservice.util;

import irish.bla.candidateservice.dto.CandidateDto;
import irish.bla.candidateservice.entity.Candidate;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static CandidateDto toDto(Candidate candidate) {
        CandidateDto candidateDto = new CandidateDto();
        BeanUtils.copyProperties(candidate,candidateDto);
        return candidateDto;
    }

    public static Candidate toEntity(CandidateDto dto) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(dto,candidate);
        return candidate;
    }
}
