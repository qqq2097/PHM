package kr.inhatc.spring.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	

	
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		// 인증되지 않은 사용자 접근 경로
		security.authorizeRequests().antMatchers("/").permitAll();
		// member 권한이 있는 사용자 접근 경로
		security.authorizeRequests().antMatchers("/").hasAnyRole("MEMBER");
		// admin 권한이 있는 사용자 접근 경로
		security.authorizeRequests().antMatchers("/user/**").hasRole("ADMIN");
		
		
		// RESTfull를 사용하기 위해서는 비활성화
		security.csrf().disable();
		
		//로그인 관련 페이지와 성공시 이동할 페이지 설정
		security.formLogin().loginPage("/login/Login").defaultSuccessUrl("/Home", true);
	
		// 실패시 이동할 페이지
		security.exceptionHandling().accessDeniedPage("/login/accessDenied");
		
		//로그아웃 요청시 세션을 강제 종료하고 시작 페이지로 이동
		security.logout().logoutUrl("/login/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
	
	
	}
	
	/*
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser(User.withUsername("user").password("{noop}1234").roles("MEMBER"))
                .withUser(User.withUsername("admin").password("{noop}1234").roles("ADMIN"));
        auth.inMemoryAuthentication()
                .withUser("manager")
                .password("{noop}manager")
                .roles("MANAGER"); */
    
   @Bean
   public PasswordEncoder passwordEncoder() {
	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
			
          }
     }
