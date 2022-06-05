package com.dwmaia.mercadolivro.config

import com.dwmaia.mercadolivro.model.enums.Roles
import com.dwmaia.mercadolivro.repository.CostumerRepository
import com.dwmaia.mercadolivro.security.AuthenticationFilter
import com.dwmaia.mercadolivro.security.AuthorizationFilter
import com.dwmaia.mercadolivro.security.JwtUtil
import com.dwmaia.mercadolivro.service.UserDetailCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
        private val customerRepository: CostumerRepository,
        private val userDetailCustomerService: UserDetailCustomService,
        private val jwtUtil: JwtUtil
) : WebSecurityConfigurerAdapter() {

    private val PUBLIC_POST_MATCHERS = arrayOf(
            "/custumers"
    )
    private val ADMIN_MATCHERS = arrayOf(
            "/report/**"
    )

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                .antMatchers(HttpMethod.POST, *ADMIN_MATCHERS).hasAnyAuthority(Roles.ADMIN.descripton)
                .anyRequest().authenticated()

        http.addFilter(AuthenticationFilter(authenticationManager(), customerRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager(), jwtUtil, userDetailCustomerService))

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailCustomerService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}