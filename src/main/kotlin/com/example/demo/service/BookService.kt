package com.example.demo.service

import com.example.demo.entity.BookInformation
import com.example.demo.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query


@Service
class BookService(private val bookRepository: BookRepository) {


    fun getfindAll(pageable: Pageable) = bookRepository.findAll(PageRequest.of(pageable.pageNumber, 5))

    fun findOne(id: Long) = bookRepository.findById(id).orElse(null)

    fun save(bookInfomation: BookInformation) = bookRepository.save(bookInfomation)

    fun delete(id: Long) = bookRepository.deleteById(id)

    fun search(name: String, pageable: Pageable) = bookRepository.findByTitleLike("%$name%", PageRequest.of(pageable.pageNumber, 10))

    fun findBydone(bool: Boolean, pageable: Pageable) = bookRepository.findByDone(bool, PageRequest.of(pageable.pageNumber, 10))

    fun allegretto() = bookRepository.genotype()

    fun findBygenre(genre: String, pageable: Pageable) :Page<BookInformation>{
        if(genre == "その他"){
            val airgenre = ""
            return bookRepository.findByGenre(airgenre, PageRequest.of(pageable.pageNumber, 10))
        }
        return bookRepository.findByGenre(genre, PageRequest.of(pageable.pageNumber, 10))
    }
}



