package edu.ntnu.idatt2105.g6.backend.security;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .headers().frameOptions().sameOrigin()
            .and()
            .authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/home", "/login", "/user/register", "/about", "/swagger/**", "/docs/**", "/swagger-ui/**")
                            .permitAll()
                        .requestMatchers("/user/**", "/auth/**", "/admin/**", "/listing/user/**")
                        .permitAll().anyRequest().authenticated()
//                        .requestMatchers("/user/**", "/auth/**")
//                            .hasAnyRole( "USER", "ADMIN") //TODO: is authenticated applied?
//                        .requestMatchers("/admin/**")
//                            .hasRole("ADMIN").anyRequest().authenticated()
            )
                //TODO: might have to create own CustomRequestMatcher for roles
//            .formLogin(form ->
//                    form.loginPage("/login")
//                            .defaultSuccessUrl("/home")//TODO add custom and to correct port!!!
//            )
//            .logout(logout ->
//                    logout.logoutUrl("/logout")
//                            .clearAuthentication(true)
//                            .invalidateHttpSession(true)
//                            .deleteCookies("JSESSION", "remember-me")//todo yeah?
//            )
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }

}
