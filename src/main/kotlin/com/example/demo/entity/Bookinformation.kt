package com.example.demo.entity

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

    val done: Boolean = false
)
