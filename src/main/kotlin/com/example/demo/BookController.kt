package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import com.example.demo.service.BookService
import com.example.demo.entity.BookInformation
import org.springframework.web.bind.annotation.*


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
        val bookinfoSelect = bookInfoservice.findOne(id)
        model.addAttribute("bookdata", bookinfoSelect)
        return "Book/edit"
    }

    @PostMapping("/update/{id}")
    fun update(@PathVariable id: Long ,@ModelAttribute bookInformation: BookInformation): String{
        bookInfoservice.save(bookInformation.copy(id= id))
        return "redirect:/"
    }

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String{
        bookInfoservice.delete(id)
        return "redirect:/"
    }
}