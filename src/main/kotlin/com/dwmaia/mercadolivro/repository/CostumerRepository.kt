package com.dwmaia.mercadolivro.repository

import com.dwmaia.mercadolivro.model.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CostumerRepository : JpaRepository<CustomerModel, Int> {
    fun existsByEmail(email: String): Boolean

    fun findByNameContaining(name: String): List<CustomerModel>

}