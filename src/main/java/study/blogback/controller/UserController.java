package study.blogback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import study.blogback.dto.request.user.PatchNicknameRequestDto;
import study.blogback.dto.request.user.PatchProfileImageRequestDto;
import study.blogback.dto.response.user.GetSignInUserResponseDto;
import study.blogback.dto.response.user.GetUserResponseDto;
import study.blogback.dto.response.user.PatchNicknameResponseDto;
import study.blogback.dto.response.user.PatchProfileImageResponseDto;
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

    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname (
            @RequestBody @Valid PatchNicknameRequestDto requestDto,
            @AuthenticationPrincipal String email
     ) {
        ResponseEntity<? super PatchNicknameResponseDto> response = userService.patchNickname(requestDto, email);
        return response;
    }

    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage (
            @RequestBody @Valid PatchProfileImageRequestDto requestDto,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PatchProfileImageResponseDto> response = userService.patchProfileImage(requestDto, email);
        return response;
    }
}
