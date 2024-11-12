package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.request.auth.IdCheckRequestDto;
import study.blogback.dto.request.auth.SignInRequestDto;
import study.blogback.dto.request.auth.SignUpRequestDto;
import study.blogback.dto.response.auth.IdCheckResponseDto;
import study.blogback.dto.response.auth.SignInResponseDto;
import study.blogback.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
}
