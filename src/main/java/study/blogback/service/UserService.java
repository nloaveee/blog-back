package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.response.user.GetSignInUserResponseDto;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
}
