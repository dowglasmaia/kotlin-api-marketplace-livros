package com.dwmaia.mercadolivro.repository

import com.dwmaia.mercadolivro.model.BookModel
import com.dwmaia.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface BookRepository : JpaRepository<BookModel, Int> {
    fun findByNameContaining(name: String,pageable: Pageable): Page<BookModel>
    fun findByStatus(status: String, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>

    //fun findAll(pageable: Pageable): Page<BookModel>

}