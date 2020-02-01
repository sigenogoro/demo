package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import com.example.demo.service.BookService
import com.example.demo.entity.BookInformation
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Pageable


@Controller
class BookController(private val bookInfoservice: BookService) {
    @GetMapping("/")
    fun index(model: Model, pageable: Pageable): String {
        val bookinfo = bookInfoservice.getfindAll(pageable)
        val changeposi = bookInfoservice.allegretto().distinct().indexOf("")
        var genretype = bookInfoservice.allegretto().distinct().toMutableList()
        genretype[changeposi] = "その他"
        model.addAttribute("bookdata", bookinfo)
        model.addAttribute("hello", genretype)
        return "Book/index"
    }

    @GetMapping("/new")
    fun new(model: Model): String{
        model.addAttribute("bookdata", BookInformation())
        return "Book/new"
    }

    @GetMapping("/show/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val bookSelect = bookInfoservice.findOne(id)
        model.addAttribute("bookdata", bookSelect)
        return "Book/show"
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
    fun searchData(model: Model, @RequestParam keyword: String, pageable: Pageable): String{
        if(keyword.isEmpty()){
            return "redirect:/"
        }else{
            val bookInfo = bookInfoservice.search(keyword, pageable)
            model.addAttribute("bookdata", bookInfo)
        }
        return "Book/index"
    }

    @GetMapping("/searchBool/{bool}")
    fun searchBool(model: Model, @PathVariable bool:Boolean, pageable: Pageable): String{
        val bookInfo = bookInfoservice.findBydone(bool, pageable)
        model.addAttribute("bookdata", bookInfo)
        return "Book/index"
    }

    @GetMapping("/searchGenre/{genre}")
    fun searchGenre(model: Model, pageable: Pageable, @PathVariable genre: String): String{
        val bookinfo = bookInfoservice.findBygenre(genre, pageable)
        val changeposi = bookInfoservice.allegretto().distinct().indexOf("")
        var genretype = bookInfoservice.allegretto().distinct().toMutableList()
        genretype[changeposi] = "その他"
        model.addAttribute("bookdata", bookinfo)
        model.addAttribute("hello", genretype)

        return "Book/index"

    }



}
