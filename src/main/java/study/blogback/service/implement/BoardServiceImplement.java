package study.blogback.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import study.blogback.dto.request.board.PostBoardRequestDto;
import study.blogback.dto.response.ResponseDto;
import study.blogback.dto.response.board.GetBoardResponseDto;
import study.blogback.dto.response.board.PostBoardResponseDto;
import study.blogback.entity.BoardEntity;
import study.blogback.entity.ImageEntity;
import study.blogback.repository.BoardRepository;
import study.blogback.repository.ImageRepository;
import study.blogback.repository.UserRepository;
import study.blogback.repository.resultSet.GetBoardResultSet;
import study.blogback.service.BoardService;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository  userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardId) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try {
            resultSet = boardRepository.getBoard(boardId);
            if (resultSet == null) {
                return GetBoardResponseDto.noExistBoard();
            }

            imageEntities = imageRepository.findByBoardId(boardId);

            BoardEntity boardEntity = boardRepository.findByBoardId(boardId);
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.success(resultSet,imageEntities);
    }

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        try {

            boolean existedEmail = userRepository.existsByEmail(email);
            if (!existedEmail) {
                return PostBoardResponseDto.notExistUser();
            }

            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            int boardId = boardEntity.getBoardId();

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image: boardImageList) {
                ImageEntity imageEntity = new ImageEntity(boardId,image);
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return PostBoardResponseDto.success();
    }
}
