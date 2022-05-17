package com.dwmaia.mercadolivro.service

import com.dwmaia.mercadolivro.model.BookModel
import com.dwmaia.mercadolivro.repository.BookRepository
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
        return repository.findById(id).orElseThrow()
    }

    fun findAll(name: String?): List<BookModel> {
        name?.let {
            return repository.findByNameContaining(name)
        }
        return repository.findAll().toList();
    }

    fun findAllByStatus(status: String): List<BookModel> {
        var list = repository.findByStatus(status).toList();
        return list;
    }

    fun delete(id: Int) {
        if (!repository.existsById(id)) {
            throw RuntimeException("Book Not Found ")
        }
        repository.deleteById(id)
    }

}
