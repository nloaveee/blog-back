package study.blogback.dto.object;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.blogback.entity.BoardListViewEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {

    private int boardId;
    private String title;
    private String content;
    private String boardTitleImage;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private LocalDateTime writeDateTime;
    private String writerNickname;
    private String writerProfileImage;

    public BoardListItem (BoardListViewEntity boardListViewEntity) {
        this.boardId = boardListViewEntity.getBoardId();
        this.title = boardListViewEntity.getTitle();
        this.content = boardListViewEntity.getContent();
        this.boardTitleImage = boardListViewEntity.getTitleImage();
        this.favoriteCount = boardListViewEntity.getFavoriteCount();
        this.commentCount = boardListViewEntity.getCommentCount();
        this.viewCount = boardListViewEntity.getViewCount();
        this.writeDateTime = boardListViewEntity.getWriteDateTime();
        this.writerNickname = boardListViewEntity.getWriterNickname();
        this.writerProfileImage = boardListViewEntity.getWriterProfileImage();
    }

    public static List<BoardListItem> getList(List<BoardListViewEntity> boardListViewEntityList) {
        List<BoardListItem> list = new ArrayList<>();
        for (BoardListViewEntity boardListViewEntity : boardListViewEntityList) {
            BoardListItem boardListItem = new BoardListItem(boardListViewEntity);
            list.add(boardListItem);
        }
        return list;
    }

}
