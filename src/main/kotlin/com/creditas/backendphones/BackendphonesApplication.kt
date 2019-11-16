package com.creditas.backendphones

import com.creditas.backendphones.user.domain.dao.IUserDao
import com.creditas.backendphones.user.infraestructure.security.JWTAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


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
                        .addFilterAfter(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java).antMatcher("/api/v1/user/logged/**")
                        .authorizeRequests()
                        .antMatchers("/api/v1/phone/**", "/api/v1/user/**").permitAll()
                        .anyRequest().authenticated()
        }
    }
}