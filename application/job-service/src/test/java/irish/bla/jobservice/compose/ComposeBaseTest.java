package irish.bla.jobservice.compose;


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
public abstract class ComposeBaseTest {
    public static final int MONGO_PORT = 27017;
    public static final String MONGO = "mongo";
    public static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/job";

    @ClassRule
    public static final DockerComposeContainer<?> compose = new DockerComposeContainer<>(new File("docker-compose.yaml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        compose
                .withEnv("HOST_ENV", "0") // "0" means assign a random port
                .withExposedService(MONGO, MONGO_PORT, Wait.forListeningPort())
                .start();
        String serviceHost = compose.getServiceHost(MONGO, MONGO_PORT);
        Integer servicePort = compose.getServicePort(MONGO, MONGO_PORT);

        String uri = String.format(MONGO_URI_FORMAT, serviceHost, servicePort);
        registry.add("spring.data.mongodb.uri", () -> uri);
    }
}
