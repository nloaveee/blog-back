package study.blogback.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import study.blogback.dto.request.user.PatchNicknameRequestDto;
import study.blogback.dto.request.user.PatchProfileImageRequestDto;
import study.blogback.dto.response.ResponseDto;
import study.blogback.dto.response.user.GetSignInUserResponseDto;
import study.blogback.dto.response.user.GetUserResponseDto;
import study.blogback.dto.response.user.PatchNicknameResponseDto;
import study.blogback.dto.response.user.PatchProfileImageResponseDto;
import study.blogback.entity.UserEntity;
import study.blogback.repository.UserRepository;
import study.blogback.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) {
                return GetUserResponseDto.noExistUser();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) {
               return GetSignInUserResponseDto.notExistUser();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    /**
     * 닉네임 수정
     */
    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto , String email) {
         try {

             UserEntity userEntity = userRepository.findByEmail(email);
             if (userEntity == null) { PatchNicknameResponseDto.noExistUser();}

             String nickname = dto.getNickname();
             boolean existedNickname = userRepository.existsByNickname(nickname);
             if (existedNickname) return PatchNicknameResponseDto.duplicateNickname();

             userEntity.setNickname(nickname);
             userRepository.save(userEntity);

         } catch (Exception exception) {
             exception.printStackTrace();
             return ResponseDto.databaseError();
         }

        return PatchNicknameResponseDto.success();
    }

    /**
     * 프로필 이미지 수정
     */
    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto , String email) {
        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) { PatchProfileImageResponseDto.noExistUser();}

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchProfileImageResponseDto.success();
    }
}
