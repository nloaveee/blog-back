package study.blogback.dto.response.board;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import study.blogback.common.ResponseCode;
import study.blogback.common.ResponseMessage;
import study.blogback.dto.object.CommentListItem;
import study.blogback.dto.response.ResponseDto;
import study.blogback.repository.resultSet.GetCommentListResultSet;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

    private List<CommentListItem> commentList;

    private GetCommentListResponseDto(List<GetCommentListResultSet> resultSet) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListItem.copyList(resultSet);
    }

    public static ResponseEntity<? super GetCommentListResponseDto> success(List<GetCommentListResultSet> resultSet) {
        GetCommentListResponseDto result = new GetCommentListResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
