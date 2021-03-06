package com.ratz.blog.config;


import com.ratz.blog.security.CustomUserDetailsService;
import com.ratz.blog.security.JwtAuthenticationEntryPoint;
import com.ratz.blog.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint authenticationEntryPoint;

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter(){
    return new JwtAuthenticationFilter();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/api/**").permitAll()
        .antMatchers("/v2/api-docs/**").permitAll()
        .antMatchers("/api/auth/**").permitAll()
        .antMatchers("/swagger-ui/**").permitAll()
        .antMatchers("/swagger-resources/**").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .anyRequest()
        .authenticated();

    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }





  //  @Bean
//  @Override
//  protected UserDetailsService userDetailsService() {
//    UserDetails ratz = User.builder().username("ratz").password(passwordEncoder().encode("123")).roles("USER").build();
//    UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("123")).roles("ADMIN").build();
//
//    return new InMemoryUserDetailsManager(ratz, admin);
//  }
}
