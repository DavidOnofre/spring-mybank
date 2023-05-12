package com.kodigo;

import com.kodigo.model.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;

@SpringBootApplication
public class SpringMybankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybankApplication.class, args);
	}

	// Enable h2 web console
	@Bean
	org.h2.tools.Server h2Server() {
		Server server = new Server();
		try {
			server.runTool("-tcp");
			server.runTool("-tcpAllowOthers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return server;
	}

}
