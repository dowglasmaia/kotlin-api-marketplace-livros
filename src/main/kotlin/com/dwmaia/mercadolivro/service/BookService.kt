package com.dwmaia.mercadolivro.service

import com.dwmaia.mercadolivro.model.BookModel
import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class BookService(val repository: BookRepository) {

    fun create(book: BookModel) {
        try {
            repository.save(book)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun update(book: BookModel) {
        try {
            repository.save(book)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun findById(id: Int): BookModel {
        return repository.findById(id).orElseThrow{
            Exception("Não encontrado para o id ${id}")
        }
    }

    fun findAll(name: String?, pageable: Pageable): Page<BookModel> {
        name?.let {
            return repository.findByNameContaining(name,pageable)
        }
        return repository.findAll(pageable)
    }

    fun findAllByStatus(status: String, pageable: Pageable): Page<BookModel> {
        val list = repository.findByStatus(status,pageable)
        return list
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = "CANCELADO";
        this.update(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = repository.findByCustomer(customer)

        for (book in books) {
            book.status = "DELETADO"
        }
        repository.saveAll(books)
    }

}
