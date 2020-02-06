package com.example.demo.service

import com.example.demo.entity.User
import org.springframework.security.core.authority.AuthorityUtils

class LoginUser(user: User): org.springframework.security.core.userdetails.User(user.email, user.password, AuthorityUtils.createAuthorityList("ROLE_USER")){
    var loginuser: User? = null

    init {
        this.loginuser = user
    }

}