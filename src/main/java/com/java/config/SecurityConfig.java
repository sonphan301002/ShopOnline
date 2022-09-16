package com.java.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.java.entity.Account;
import com.java.service.AccountService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Cung cấp nguồn dữ liệu
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// đưa nguồn security vào
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findById(username); // đưa vào userDatail
				String password = bCryptPasswordEncoder.encode(user.getPassword()); // tìm acc bằng user sau đó mã hóa
																					// pass
				String[] roles = user.getAuthorities().stream() // đổi vai trò người dùng sang mãng
						.map(er -> er.getRole().getId()).collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build(); // lấy user pass role từ
																							// database
			} catch (Exception e) {
				// TODO: handle exception
				throw new UsernameNotFoundException(username + "no found");
			}
		});
	}

	// Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeHttpRequests().antMatchers("/order/**").authenticated().antMatchers("/admin/**")
				.hasAnyRole("STAF", "DIRE").antMatchers("/rest/authorities").hasRole("DIRE").anyRequest().permitAll();

		http.formLogin().loginPage("/account/login/form").loginProcessingUrl("/account/login")
				.defaultSuccessUrl("/account/login/success", false) // false là đăng nhập k nhất thiết phải trở về
																		// trang success
				.failureUrl("/security/login/error");
		
		http.rememberMe().tokenValiditySeconds(86400);

		http.exceptionHandling().accessDeniedPage("/account/unauthoried");

		http.logout().logoutUrl("/account/logoff").logoutSuccessUrl("/account/logoff/success");
	}

	// cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// cho phép truy xuất REST API từ bên ngoài
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
