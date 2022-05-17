package com.dwmaia.mercadolivro.controller

import com.dwmaia.mercadolivro.controller.request.book.PostBookRequestDTO
import com.dwmaia.mercadolivro.controller.request.book.PutBookRequestDTO
import com.dwmaia.mercadolivro.extension.toBookModel
import com.dwmaia.mercadolivro.model.BookModel
import com.dwmaia.mercadolivro.service.BookService

import com.dwmaia.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody request: PutBookRequestDTO): ResponseEntity<Void> {
        val book = bookService.findById(id);
        bookService.update(request.toBookModel(book));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<BookModel> {
        val book = bookService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @GetMapping()
    fun getAll(@RequestParam name: String?): ResponseEntity<List<BookModel>> {
        val list = bookService.findAll(name);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/status")
    fun getAllByStatus(@RequestParam status: String): ResponseEntity<List<BookModel>> {
        val list = bookService.findAllByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}