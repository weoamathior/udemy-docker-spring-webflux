package irish.bla.candidateservice;

import irish.bla.candidateservice.dto.Service;
import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.File;

@Testcontainers
public abstract class BaseTest {
    public static final Service MONGO = Service.create(
            "mongo",
            27017,
            "0",
            "mongodb://candidate_user:candidate_password@%s:%s/candidate",
            "HOST_PORT1"
    )
    public static final Service JOB = Service.create(
            "job-mock",
            1080,
            "0",
            "http://%s:%s/job/",
            "HOST_PORT2"
    )

    @ClassRule
    public static final DockerComposeContainer<?> compose = new DockerComposeContainer<>(new File("docker-compose.yaml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        compose
                .withEnv(MONGO.getHostPortEnvVariable(), MONGO.getHostPort())
                .withEnv(JOB.getHostPortEnvVariable(), JOB.getHostPort())
                .withExposedService(MONGO.getName(), MONGO.getPort(), Wait.forListeningPort())
                .withExposedService(JOB.getName(), JOB.getPort(), Wait.forListeningPort())
                .start();
        String mongoHost = compose.getServiceHost(MONGO.getName(), MONGO.getPort());
        Integer mongoPort = compose.getServicePort(MONGO.getName(), MONGO.getPort());

        String uri = String.format(MONGO.getUri(), mongoHost, mongoPort);
        registry.add("spring.data.mongodb.uri", () -> uri);

        String jobHost = compose.getServiceHost(JOB.getName(), JOB.getPort());
        Integer jobPort = compose.getServicePort(JOB.getName(), JOB.getPort());
        String joburi = String.format(JOB.getUri(), jobHost, jobPort);
        registry.add("job.service.url", () -> joburi);
    }
}