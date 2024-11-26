package study.blogback.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.blogback.common.CertificationNumber;
import study.blogback.dto.request.auth.*;
import study.blogback.dto.response.ResponseDto;
import study.blogback.dto.response.auth.*;
import study.blogback.entity.CertificationEntity;
import study.blogback.entity.UserEntity;
import study.blogback.provider.EmailProvider;
import study.blogback.provider.JWTProvider;
import study.blogback.repository.CertificationRepository;
import study.blogback.repository.UserRepository;
import study.blogback.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private final EmailProvider emailProvider;
    private final CertificationRepository certificationRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 아이디 중복 체크
     */
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {

            String userId =dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) {
                return IdCheckResponseDto.duplicateId();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    /**
     * 이메일 인증
     */
    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {

            String userId =dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsById(userId);
            if (isExistId) {
                return EmailCertificationResponseDto.duplicateId();
            }

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) {
                return EmailCertificationResponseDto.mailSendFail();
            }

            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    /**
     * 이메일 인증번호 확인
     */
    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {

            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            if (certificationEntity == null) {
                return CheckCertificationResponseDto.certificationFail();
            }

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) {
                return CheckCertificationResponseDto.certificationFail();
            }

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    /**
     * 회원가입
     */
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {
            String userId = dto.getUserId();
            boolean existedById = userRepository.existsByUserId(userId);
            if (existedById) {
                return IdCheckResponseDto.duplicateId();
            }

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

            String certificationNumber = dto.getCertificationNumber();
            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) {
                return SignUpResponseDto.certificationFail();
            }

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            certificationRepository.deleteByUserId(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    /**
     * 로그인
     */
    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;

        try {
            String userId = dto.getId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) {
                return SignInResponseDto.signInFail();
            }

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) {
                return SignInResponseDto.signInFail();
            }

            token = jwtProvider.create(userId);


        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }


}
