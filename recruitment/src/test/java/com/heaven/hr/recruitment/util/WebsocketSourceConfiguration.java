package com.heaven.hr.recruitment.util;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebsocketSourceConfiguration {
	  @Bean 
	  ServletWebServerFactory servletWebServerFactory(){
	  return new TomcatServletWebServerFactory();
	  }
	}