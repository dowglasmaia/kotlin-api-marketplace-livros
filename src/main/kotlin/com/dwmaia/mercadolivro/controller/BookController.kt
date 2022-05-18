package com.dwmaia.mercadolivro.controller

import com.dwmaia.mercadolivro.controller.request.book.*
import com.dwmaia.mercadolivro.extension.*
import com.dwmaia.mercadolivro.controller.response.book.BookModelResponse
import com.dwmaia.mercadolivro.service.BookService
import com.dwmaia.mercadolivro.service.CustomerService

import org.springframework.http.*
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(
        val bookService: BookService,
        val customerService: CustomerService
) {

    @PostMapping()
    fun create(@Valid @RequestBody request: PostBookRequestDTO): ResponseEntity<Void> {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update( @PathVariable id: Int, @RequestBody request: PutBookRequestDTO): ResponseEntity<Void> {
        val book = bookService.findById(id)
        bookService.update(request.toBookModel(book))
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<BookModelResponse> {
        val book = bookService.findById(id).toResponse()
        return ResponseEntity.status(HttpStatus.OK).body(book)
    }

    @GetMapping()
    fun getAll(@RequestParam name: String?): ResponseEntity<List<BookModelResponse>> {
        val list = bookService.findAll(name).map { it.toResponse() }
        return ResponseEntity.status(HttpStatus.OK).body(list)
    }

    @GetMapping("/status")
    fun getAllByStatus(@RequestParam status: String): ResponseEntity<List<BookModelResponse>> {
        val list = bookService.findAllByStatus(status).map { it.toResponse() }
        return ResponseEntity.status(HttpStatus.OK).body(list)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        bookService.delete(id)
        return ResponseEntity.noContent().build()
    }

}