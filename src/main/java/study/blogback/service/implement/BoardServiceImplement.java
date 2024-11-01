package study.blogback.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import study.blogback.dto.request.board.PostBoardRequestDto;
import study.blogback.dto.request.board.PostCommentRequestDto;
import study.blogback.dto.response.ResponseDto;
import study.blogback.dto.response.board.*;
import study.blogback.entity.BoardEntity;
import study.blogback.entity.CommentEntity;
import study.blogback.entity.FavoriteEntity;
import study.blogback.entity.ImageEntity;
import study.blogback.repository.*;
import study.blogback.repository.resultSet.GetBoardResultSet;
import study.blogback.repository.resultSet.GetCommentListResultSet;
import study.blogback.repository.resultSet.GetFavoriteListResultSet;
import study.blogback.service.BoardService;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository  userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepositiory commentRepository;

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
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardId) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

        try {
            boolean existedBoard = boardRepository.existsByBoardId(boardId);
            if (!existedBoard) {
                return GetFavoriteListResponseDto.noExistBoard();
            }

            resultSets = favoriteRepository.getFavoriteList(boardId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardId) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsByBoardId(boardId);
            if (!existedBoard) {
                return GetCommentListResponseDto.noExistBoard();
            }

            resultSets = commentRepository.getCommentList(boardId);

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommentListResponseDto.success(resultSets);
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

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardId, String email) {
        try {

            BoardEntity boardEntity = boardRepository.findByBoardId(boardId);
            if (boardEntity == null) {
                return PostCommentResponseDto.noExistBoard();
            }
            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) {
                return PostCommentResponseDto.noExistUser();
            }

            CommentEntity commentEntity = new CommentEntity(dto, boardId, email);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardId, String email) {
        try {
            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) {
                return PutFavoriteResponseDto.noExistUser();
            }

            BoardEntity boardEntity = boardRepository.findByBoardId(boardId);
            if (boardEntity == null) {
                return PutFavoriteResponseDto.noExistBoard();
            }

            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardIdAndUserEmail(boardId,email);
            if (favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(email,boardId);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            }
            else {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }
}
