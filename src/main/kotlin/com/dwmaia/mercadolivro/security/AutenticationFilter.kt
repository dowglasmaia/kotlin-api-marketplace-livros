package com.dwmaia.mercadolivro.security

import com.dwmaia.mercadolivro.controller.request.login.LoginRequest
import com.dwmaia.mercadolivro.exception.AuthenticationException
import com.dwmaia.mercadolivro.repository.CostumerRepository
import com.dwmaia.mercadolivro.service.CustomerService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticationFilter(
        authenticationManager: AuthenticationManager,
        private val customerRepository: CostumerRepository
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest,
                                       response: HttpServletResponse
    ): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val id = customerRepository.findByEmail(loginRequest.email)?.id

            val authToken = UsernamePasswordAuthenticationToken(id, loginRequest.password)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            throw AuthenticationException("Falha ao Autenticar", "999")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain,
                                          authResult: Authentication
    ) {
        val id = (authResult.principal as UserCustomerDetails).id
        response.addHeader("Authorization", "123456")
    }

}