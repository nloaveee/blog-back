package study.blogback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board_list_view")
@Table(name="board_list_view")
public class BoardListViewEntity {

    @Id
    private int boardId;
    private String title;
    private String content;
    private String image;
    private String commentCount;
    private String favoriteCount;
    private String viewCount;
    private LocalDateTime writeDateTime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;
}
