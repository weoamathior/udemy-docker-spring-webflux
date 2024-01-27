package irish.bla.candidateservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    private String id;
    private String name;
    private Set<String> skills;

}
