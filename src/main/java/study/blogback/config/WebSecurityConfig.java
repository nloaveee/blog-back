package study.blogback.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import study.blogback.exception.OAuth2SuccessHandler;
import study.blogback.filter.JwtAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final DefaultOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;

    public WebSecurityConfig(DefaultOAuth2UserService oAuth2UserService, OAuth2SuccessHandler successHandler) {
        this.oAuth2UserService = oAuth2UserService;
        this.successHandler = successHandler;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    protected  SecurityFilterChain configure(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        //cors
        http
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()));

        //csrf disable
        http
                .csrf(CsrfConfigurer::disable);

        //http basic 인증 방식 disable
        http
                .httpBasic(HttpBasicConfigurer::disable);

        //세션 설정
        http
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //경로별 인가 작업
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/favicon.ico", "/api/v1/auth/**", "/api/v1/search/**", "/file/**", "/oauth2/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/board/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/**").hasRole("USER")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated());

        http
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/oauth2"))
                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                        .successHandler(successHandler));


        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new FailedAuthenticationEntryPoint()));

        return http.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    static class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\": \"AF\", \"message\": \"Authentication failed\"}");
        }
    }
}
