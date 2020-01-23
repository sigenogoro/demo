package com.example.demo.service

import com.example.demo.entity.BookInformation
import com.example.demo.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository){

    fun findAll()  = bookRepository.findAll()

    fun findOne(id: Long) = bookRepository.findById(id).orElse(null)

    fun save(bookIn: BookInformation) = bookRepository.save(bookIn)


}