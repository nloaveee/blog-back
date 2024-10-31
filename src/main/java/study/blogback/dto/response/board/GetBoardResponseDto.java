package study.blogback.dto.response.board;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import study.blogback.common.ResponseCode;
import study.blogback.common.ResponseMessage;
import study.blogback.dto.response.ResponseDto;
import study.blogback.entity.ImageEntity;
import study.blogback.repository.resultSet.GetBoardResultSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetBoardResponseDto extends ResponseDto {

    private int boardId;
    private String title;
    private String content;
    private List<String> boardImageList;
    private LocalDateTime writeDateTime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    private GetBoardResponseDto(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity imageEntity : imageEntities) {
            String boardImage = imageEntity.getImage();
            boardImageList.add(boardImage);
        }
        this.boardId = resultSet.getBoardId();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.boardImageList =boardImageList ;
        this.writeDateTime = LocalDateTime.now();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();
    }

    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        GetBoardResponseDto result  = new GetBoardResponseDto(resultSet, imageEntities );
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
