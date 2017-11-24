package com.atsistemas.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sun.org.apache.xpath.internal.operations.And;

/**
 * Created by informatica on 26/02/16.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private EntryPointUnauthorizedHandler authenticationEntryPoint;
	@Autowired
	private AuthFailure authenticationFailureHandler;
	@Autowired
	private AuthSuccess authenticationSuccessHandler;
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	private UserDetailServiceImpl userDetailService;
	@SuppressWarnings("rawtypes")
	@Autowired
    private FindByIndexNameSessionRepository sessionRepository;


	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;

	}

	@Autowired
	public void configAuthBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());

	}

    @Bean
    @SuppressWarnings("unchecked")
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(this.sessionRepository);
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * Production ready configuration. Uncomment when in production:
		 */

		// To login via parameter: http://localhost:8080/EjercicioConcesionario/login
		// format: x-www-form-urlencoded
		// To login via url: http://localhost:8080/EjercicioConcesionario/login?username=username&password=password
		
//		http
//			.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class).authorizeRequests()
//			.antMatchers("/login").permitAll().antMatchers("/logout").permitAll()
		
// 			This two lines blocks the entire app		
//			.and()
//			.authorizeRequests().anyRequest().authenticated();

		http
			.formLogin().loginProcessingUrl("/login").loginPage("/login").permitAll().usernameParameter("username")
			.passwordParameter("password").successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler).and().rememberMe().userDetailsService(userDetailService).
			rememberMeCookieName("remember-me").tokenValiditySeconds(22000)//To work only with https: .useSecureCookie(true)
			.and().exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement().maximumSessions(1)
			.sessionRegistry(sessionRegistry()); //This is so Spring session handle the number of concurrent sessions for  single user
			// set to true to prevent logins after reaching max sessions:
            //.maxSessionsPreventsLogin(false)
			
			//This may work or may not
			//.and().sessionFixation().migrateSession();

		http
			.logout().logoutSuccessHandler(logoutSuccessHandler).logoutUrl("/logout");

		// */

		// In Development:

		 http
		 .csrf().disable()
		 .authorizeRequests().anyRequest().permitAll();
		 
		 // Extra rules for testing
		 
//		 .and()
//		 .formLogin().loginPage("/login").permitAll().successHandler(authenticationSuccessHandler)
//		 .failureHandler(authenticationFailureHandler)
//		 .and()
//		 .rememberMe().rememberMeParameter("remember-me").tokenValiditySeconds(2000)
//		 .and()
//		 .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//		 .and()
//		 .sessionManagement().maximumSessions(1);
	}

}
