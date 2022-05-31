package com.dwmaia.mercadolivro.service

import com.dwmaia.mercadolivro.exception.AuthenticationException
import com.dwmaia.mercadolivro.repository.CostumerRepository
import com.dwmaia.mercadolivro.security.UserCustomerDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailCustomService(
        private val customerRepository: CostumerRepository
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt()).orElseThrow()
        AuthenticationException("Usuário não encontrado", "404")

        return UserCustomerDetails(customer)
    }
}