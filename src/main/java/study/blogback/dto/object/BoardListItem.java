package study.blogback.dto.object;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

}
