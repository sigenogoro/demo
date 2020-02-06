package com.example.demo.entity



import org.springframework.format.annotation.DateTimeFormat

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name="Book_info")
data class BookInformation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @NotBlank
        val title: String = "",

        val done: Boolean = false,

        val impression: String = "",

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val startdate: LocalDate? = null,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val enddate: LocalDate? = null,

        val genre: String? = ""

)

@Entity
@Table(name="users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @NotBlank
        @Column(nullable = false)
        val nickname: String = "",

        @NotBlank
        @Size(min=10)
        @Column(nullable = false)
        var password: String? = "",

        @NotBlank
        @Email
        @Column(nullable = false)
        val email:String = ""




)



