package com.m2p.web.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@SpringBootApplication
@ComponentScan("com.m2p.web")
@EnableMongoRepositories("com.m2p.web.repository")
@PropertySource("classpath:config_local.properties")
public class UiApplication {

    /*@Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }*/
    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }

        @Bean
        MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            factory.setMaxFileSize("12800KB");
            factory.setMaxRequestSize("12800KB");
            return factory.createMultipartConfig();
        }




	@Configuration
	@EnableWebSecurity
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("sriramks85@gmail.com").password("sriram").roles("USER")
                    .and()
                    .withUser("senthil@m2p.in").password("senthil").roles("USER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/login.html").permitAll()
                    .antMatchers("/bower_components/**").permitAll()
                    .antMatchers("/scripts/**").permitAll()
                    .antMatchers("/styles/**").permitAll()
                    .antMatchers("/fonts/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/**").hasRole("USER")
                    .anyRequest().anonymous()
                    .and().csrf().disable()
                    .formLogin()
                    .loginPage("/login.html")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login").successHandler(new SimpleUrlAuthenticationSuccessHandler("/"))
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/");
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
	}

}
