package com.dwmaia.mercadolivro.repository

import com.dwmaia.mercadolivro.model.BookModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookModel,Int>  {

    fun findByNameContaining(name:String):List<BookModel>

    fun findByStatus(status:String):List<BookModel>

}