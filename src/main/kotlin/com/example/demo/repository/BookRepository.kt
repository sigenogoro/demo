package com.example.demo.repository

import com.example.demo.entity.BookInformation
import com.example.demo.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query


@Repository
interface BookRepository: JpaRepository<BookInformation, Long>{
    fun findByTitleLike(@Param("keyword") keyword: String, pageable: Pageable): Page<BookInformation>

    override fun findAll(pageable: Pageable): Page<BookInformation>

    fun findByDone(@Param("bool") bool: Boolean, pageable: Pageable): Page<BookInformation>

    @Query("SELECT genre from Book_info", nativeQuery = true)
    fun genotype(): MutableList<String>

    fun findByGenre(@Param("genre") genre: String, pageable: Pageable): Page<BookInformation>


}

@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findByEmail(email: String): User
}