package study.blogback.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.blogback.dto.request.auth.SignInRequestDto;
import study.blogback.dto.request.auth.SignUpRequestDto;
import study.blogback.dto.response.ResponseDto;
import study.blogback.dto.response.auth.SignInResponseDto;
import study.blogback.dto.response.auth.SignUpResponseDto;
import study.blogback.entity.UserEntity;
import study.blogback.repository.UserRepository;
import study.blogback.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {
            String email = dto.getEmail();
            boolean existedByEmail = userRepository.existsByEmail(email);
            if (existedByEmail) {
                return SignUpResponseDto.duplicateEmail();
            }

            String nickname = dto.getNickname();
            boolean existedByNickname = userRepository.existsByNickname(nickname);
            if (existedByNickname) {
                return SignUpResponseDto.duplicateNickname();
            }


            String telNumber = dto.getTelNumber();
            boolean existedByTelNumber = userRepository.existsByTelNumber(telNumber);
            if (existedByTelNumber) {
                return SignUpResponseDto.duplicateTelNumber();
            }

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;

        try {

            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) {
                return SignInResponseDto.signInFail();
            }

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) {
                return SignInResponseDto.signInFail();
            }


        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}
