package gouri.ibk.interactivetrader;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@Configuration
@Profile("test")
@EnableJpaRepositories(basePackages = {"gouri.ibk.interactivetrader.repo"})
@TestPropertySource(locations={"classpath:application-test.properties"})
public class InteractiveTraderServiceTestConfig {



}
