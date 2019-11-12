package com.creditas.backendphones

import com.creditas.backendphones.user.infraestructure.security.JWTAuthorizationFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@SpringBootApplication
class BackendphonesApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<BackendphonesApplication>(*args)
        }
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig : WebSecurityConfigurerAdapter() {
        override fun configure(httpSecurity: HttpSecurity) {

            httpSecurity
                    .cors()
                    .and()
                    .csrf().disable()
                    //.addFilterAfter(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
                    .authorizeRequests()
                    .antMatchers("/api/v1/phone/**", "/api/v1/user/**").permitAll()
                    //.antMatchers("/api/v1/phone/**", "/api/v1/user/login").permitAll()
                    .anyRequest().authenticated()

        }
    }
}