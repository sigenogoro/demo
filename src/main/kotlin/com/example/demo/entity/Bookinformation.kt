package com.example.demo.entity


import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
@Table(name="Book_info")
data class BookInformation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get:NotBlank
    val title: String = "",

    val done: Boolean = false,

    val impression: String = "",

    val startdate: LocalDate = LocalDate.now(),

    val enddate: LocalDate = LocalDate.now(),

    val genre: String = ""


)
