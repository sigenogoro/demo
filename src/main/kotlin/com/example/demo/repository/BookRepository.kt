package com.example.demo.repository

import com.example.demo.entity.BookInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page

import org.springframework.data.domain.Pageable


@Repository
interface BookRepository: JpaRepository<BookInformation, Long>{
    @Query("from BookInformation where title like %:keyword% ")
    fun findByTitleLike(@Param("keyword") keyword: String): List<BookInformation>

    override fun findAll(pageable: Pageable): Page<BookInformation>

}