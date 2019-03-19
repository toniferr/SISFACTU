package com.toni.ferreiro;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.toni.ferreiro.auth.handler.LoginSuccessHandler;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSegurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired 
	private DataSource datasource;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
//				.antMatchers("/ver/**").hasAnyRole("USER").antMatchers("/uploads/**").hasAnyRole("USER")
//				.antMatchers("/form/**").hasAnyRole("ADMIN").antMatchers("/eliminar/**").hasAnyRole("ADMIN")
//				.antMatchers("/factura/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated().and().formLogin()
				.successHandler(successHandler)
				.loginPage("/login").permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/error_403");
	}

	@Autowired
	public void ConfigurerGlobal(AuthenticationManagerBuilder build) throws Exception {

//		UserBuilder users = User.withDefaultPasswordEncoder();	con spring boot 1
		/*PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);

		build.inMemoryAuthentication().withUser(users.username("admin").password("1234").roles("ADMIN", "USER"))
				.withUser(users.username("toni").password("1234").roles("USER"));*/
		build.jdbcAuthentication().dataSource(datasource).passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on a.user_id=u.id where u.username=?");
	}

}
