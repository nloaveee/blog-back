package study.blogback.dto.response.auth;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import study.blogback.common.ResponseCode;
import study.blogback.common.ResponseMessage;
import study.blogback.dto.response.ResponseDto;

@Getter
public class IdCheckResponseDto extends ResponseDto {


    public IdCheckResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<IdCheckResponseDto> success() {
        IdCheckResponseDto result = new IdCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
