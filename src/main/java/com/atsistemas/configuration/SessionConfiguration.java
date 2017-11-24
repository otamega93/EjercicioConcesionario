package com.atsistemas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@EnableHazelcastHttpSession
@Configuration
public class SessionConfiguration {

	@Bean
	public HazelcastInstance hazelcastInstance() {
		MapAttributeConfig attributeConfig = new MapAttributeConfig()
				.setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
				.setExtractor(PrincipalNameExtractor.class.getName());

		Config config = new Config();

		config.getMapConfig("spring:session:sessions").addMapAttributeConfig(attributeConfig)
				.addMapIndexConfig(new MapIndexConfig(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("JSESSIONID");

		/*
		 * If setCookieMaxAge is used, then it works like a persistent cookie
		 * (like remember me cookie). If it's not, then it works like a regular
		 * cookie (expires when the browser is closed)
		 */
		// serializer.setCookieMaxAge(20000);
		serializer.setUseHttpOnlyCookie(true);
		// serializer.setCookiePath("/");
		// serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		return serializer;
	}

//  Doesnt work at all (and it's from the official doc)
//	@Bean
//	public RememberMeServices rememberMeServices() {
//		SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
//		// optionally customize
//		rememberMeServices.setAlwaysRemember(true);
//		rememberMeServices.setRememberMeParameterName("remember-me");
//		return rememberMeServices;
//	}
	
}
