package gouri.ibk.interactivetrader;

import gouri.ibk.interactivetrader.tools.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InteractiveTraderServiceApplication {

	static ConfigurableApplicationContext CONTEXT;

	public static ConfigurableApplicationContext getContext() {
		return CONTEXT;
	}

	public static void main(String[] args) {
        CONTEXT = SpringApplication.run(
        	new Class<?>[]{InteractiveTraderAppConfig.class, InteractiveTraderServiceApplication.class}, args);

		DataInitializer di = getContext().getBean(DataInitializer.class);
		di.initData();

    }

}
