package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.request.user.PatchNicknameRequestDto;
import study.blogback.dto.request.user.PatchProfileImageRequestDto;
import study.blogback.dto.response.user.GetSignInUserResponseDto;
import study.blogback.dto.response.user.GetUserResponseDto;
import study.blogback.dto.response.user.PatchNicknameResponseDto;
import study.blogback.dto.response.user.PatchProfileImageResponseDto;

public interface UserService {

    ResponseEntity<? super GetUserResponseDto> getUser(String id);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String id);

    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto , String email);
}
