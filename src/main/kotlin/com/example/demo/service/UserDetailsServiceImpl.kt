package com.example.demo.service

import com.example.demo.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class UserDetailsServiceImpl: UserDetailsService{

    @Autowired
    lateinit var userSerive: UserSerive



    override fun loadUserByUsername(email: String): UserDetails {
        var user: User? = null
        try {
            user = userSerive.findByEmail(email)

        }catch (e:Exception){
            throw UsernameNotFoundException("can't get user")
        }

        return LoginUser(user)
    }

}
