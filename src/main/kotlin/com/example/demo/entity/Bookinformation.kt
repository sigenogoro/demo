package com.example.demo.entity

import javax.persistence.*

@Entity
@Table(name="Book_info")
data class BookInformation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name="title", nullable = false)
    val title: String = "",

    @Column(name="done", nullable = false)
    val done: Boolean = false
)
