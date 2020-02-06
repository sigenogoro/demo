package com.example.demo.service


import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserSerive(private val userRepository: UserRepository){

    @Autowired
    lateinit var  passwordEncoder: PasswordEncoder



    fun findByEmail(email: String) = userRepository.findByEmail(email)

    fun create(user: User, rawPassword: String?): User {
        val encodedPassword: String = passwordEncoder.encode(rawPassword)
        user.password = encodedPassword
        return userRepository.save(user)
    }

}



