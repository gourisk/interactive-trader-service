package gouri.ibk.interactivetrader;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.jms.Topic;
import java.util.Arrays;
import java.util.UUID;

@Configuration
@EnableJpaRepositories(basePackages = {"gouri.ibk.interactivetrader.repo"})
public class InteractiveTraderAppConfig {

    public int doTest(int[] numbers) {
        return Arrays.stream(numbers)
            .boxed()
            .map(Integer::signum)
            .reduce((a, b) -> a * b)
            .orElse(0);
    }

    @Bean
    @Scope("Prototype")
    public Topic jmsTopic() {
        return new ActiveMQTopic("trader: " + UUID.randomUUID());
    }

}
