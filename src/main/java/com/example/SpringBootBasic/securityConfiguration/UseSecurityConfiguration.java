package com.example.SpringBootBasic.securityConfiguration;

import com.example.SpringBootBasic.service.UserDetailServiceSimple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class UseSecurityConfiguration extends WebSecurityConfigurerAdapter { // deprecate

    @Bean
    public PasswordEncoder passwordEncoder()  {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailServiceSimple userDetailsService() {
        return new UserDetailServiceSimple();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("admin_zorba").password(passwordEncoder().encode("admin123")).roles("ADMIN")
                .and()
                .withUser("admin_khanal").password(passwordEncoder().encode("admin@123")).roles("ADMIN" , "USER")
                .and()
                .withUser("admin+").password(passwordEncoder().encode("admin@@123")).roles("USER");
    }
*/
    /*@Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring()
                .antMatchers("/user/saveUserData")
                .antMatchers("/test/saveData");
    }*/
    @Override
    public  void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/test/saveData") .hasAuthority("ADMIN")// permitAll() for save the data without authentication
                .antMatchers(HttpMethod.GET , "/test/getDataFromFinalProject").hasAnyAuthority("ADMIN" , "USER")
                .antMatchers(HttpMethod.PUT,"/test/updateDataBaseOnCustId" ).hasAuthority("USER")
                .antMatchers(HttpMethod.POST,"/user/saveUserData").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/user/updateUserInfoBasedOnUserId").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT,"/user/updateUserInfo").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT,"/test/updateCustInfo").hasAuthority("USER")
                .antMatchers(HttpMethod.GET,"/user/fetchUserData").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}


