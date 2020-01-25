package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import com.example.demo.service.BookService
import com.example.demo.entity.BookInformation
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*



@Controller
class BookController(private val bookInfoservice: BookService) {
    @GetMapping("/")
    fun index(model: Model): String {
        val bookinfo = bookInfoservice.findAll()
        model.addAttribute("bookdata", bookinfo)
        return "Book/index"
    }

    @GetMapping("/new")
    fun new(model: Model): String{
        model.addAttribute("bookdata", BookInformation())
        return "Book/new"
    }



    @PostMapping("/new")
    fun create(@Validated  @ModelAttribute("bookdata") bookdata: BookInformation, bindingResult: BindingResult, model: Model): String{
        if(bindingResult.hasErrors()){
            return "Book/new"
        }
        bookInfoservice.save(bookdata)
        return "redirect:/"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val bookinfoSelect = bookInfoservice.findOne(id)
        model.addAttribute("bookdata", bookinfoSelect)
        return "Book/edit"
    }

    @PostMapping( "/update/{id}")
    fun update(@PathVariable id: Long, @Validated @ModelAttribute("bookdata") bookdata: BookInformation, bindingResult: BindingResult): String{
        if(bindingResult.hasErrors()){
            return "Book/edit"
        }
        bookInfoservice.save(bookdata.copy(id= id))
        return "redirect:/"
    }
    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String{
        bookInfoservice.delete(id)
        return "redirect:/"
    }

    @GetMapping("/search/")
    fun searchData(model: Model, @RequestParam keyword: String): String{
        if(keyword.isEmpty()){
            val bookinfo = bookInfoservice.findAll()
            model.addAttribute("bookdata", bookinfo)
        }else{
            val bookInfo = bookInfoservice.search(keyword)
            model.addAttribute("bookdata", bookInfo)
        }
        return "Book/index"
    }
}