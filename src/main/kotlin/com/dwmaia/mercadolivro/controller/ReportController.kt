package com.dwmaia.mercadolivro.controller


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reports")
class ReportController() {

    @GetMapping()
    fun report(): ResponseEntity<String> {
        val reports = "This is a Report";
        return ResponseEntity.status(HttpStatus.OK).body(reports)
    }
}