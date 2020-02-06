package com.example.demo.service


import com.example.demo.service.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**", "/shutdown")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/login").permitAll() //認証していないユーザーでも入れるURL
                .antMatchers("/signup").permitAll()
                .anyRequest().authenticated() //それ以外のURLは全て禁止

        http.formLogin()
                .loginProcessingUrl("/login") //ログインの処理を行うURL 認証を行うURL
                .loginPage("/login") //ログインフォームへのURL
                .failureUrl("/login/?error")
                .defaultSuccessUrl("/") // ログインが成功したときに飛ぶURL
                .usernameParameter("email")
                .passwordParameter("password") // パラメーター指定
                .and()

        http.logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/login")
    }



    @Configuration
    class AuthenticationConfiguration : GlobalAuthenticationConfigurerAdapter() {

        @Autowired
        var userDetailsService : UserDetailsServiceImpl = UserDetailsServiceImpl() ;

        @Bean
        fun passwordEncoder(): PasswordEncoder {
            return BCryptPasswordEncoder()
        }


        override fun init(auth: AuthenticationManagerBuilder) {
            auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder())
        }
    }

}

