package com.example.demo.service

import com.example.demo.entity.BookInformation
import com.example.demo.repository.BookRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class BookService(private val bookRepository: BookRepository){
    val i = PageRequest.of(0, 5)
    fun getfindAll(pageable: Pageable) = bookRepository.findAll(i)

    fun findOne(id: Long) = bookRepository.findById(id).orElse(null)

    fun save(bookInfomation: BookInformation) = bookRepository.save(bookInfomation)

    fun delete(id: Long) = bookRepository.deleteById(id)

    fun search(name: String) = bookRepository.findByTitleLike(name)


}