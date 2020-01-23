package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import com.example.demo.service.BookService
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import com.example.demo.entity.BookInformation

import org.springframework.web.bind.annotation.PathVariable


@Controller
class BookController(private val bookInfoservice: BookService) {
    @GetMapping("/")
    fun index(model: Model): String {
        val bookinfo = bookInfoservice.findAll()
        model.addAttribute("bookdata", bookinfo)
        return "Book/index"
    }

    @GetMapping("/create_form")
    fun new(model: Model): String{
        return "Book/new"
    }

    @PostMapping("/")
    fun create(@ModelAttribute bookInformation: BookInformation): String{
        bookInfoservice.save(bookInformation)
        return "redirect:/"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val bookinfo_select = bookInfoservice.findOne(id)
        model.addAttribute("bookdata", bookinfo_select)
        return "Book/edit"
    }
}