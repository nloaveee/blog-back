package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.response.user.GetSignInUserResponseDto;
import study.blogback.dto.response.user.GetUserResponseDto;

public interface UserService {

    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
}
