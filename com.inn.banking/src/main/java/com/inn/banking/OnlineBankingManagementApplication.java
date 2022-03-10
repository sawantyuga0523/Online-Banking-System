package com.inn.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBankingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingManagementApplication.class, args);
	}
	
	/* @Bean
	    public EmbeddedServletContainerCustomizer containerCustomizer() {
	        return (container -> {
	            container.setSessionTimeout(60);  // session timeout value
	        });
	    }*/

}
