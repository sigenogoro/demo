package com.example.demo.entity


import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank



@Entity
@Table(name="Book_info")
data class BookInformation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @get:NotBlank
        val title: String = "",

        val done: Boolean = false,

        val impression: String = "",

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val startdate: LocalDate? = null,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val enddate: LocalDate? = null,

        val genre: String? = ""



)

