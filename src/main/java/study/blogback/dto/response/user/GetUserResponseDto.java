package study.blogback.dto.response.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import study.blogback.common.ResponseCode;
import study.blogback.common.ResponseMessage;
import study.blogback.dto.response.ResponseDto;
import study.blogback.entity.UserEntity;

@Getter
public class GetUserResponseDto extends ResponseDto {

    private String email;
    private String nickname;
    private String profilImgae;

    public GetUserResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profilImgae = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity) {
        GetUserResponseDto result = new GetUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser () {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
