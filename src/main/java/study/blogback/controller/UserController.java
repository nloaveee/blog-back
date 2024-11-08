package study.blogback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.blogback.dto.response.user.GetSignInUserResponseDto;
import study.blogback.dto.response.user.GetUserResponseDto;
import study.blogback.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserResponseDto> getUser
            (@PathVariable("email") String email) {
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(email);
        return response;
    }

    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser
            (@AuthenticationPrincipal String email) {
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);
        return response;
    }
}
