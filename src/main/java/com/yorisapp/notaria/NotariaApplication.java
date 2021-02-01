package com.yorisapp.notaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.yorisapp.notaria"})
@EntityScan(basePackages = {"com.yorisapp.notaria"})
@SpringBootApplication
public class NotariaApplication{
//public class NotariaApplication extends SpringBootServletInitializer {

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NotariaApplication.class);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(NotariaApplication.class, args);
	}

}
