package pl.java.spring.web.project.clientappproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ClientAppProjectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ClientAppProjectApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ClientAppProjectApplication.class, args);
	}
}
