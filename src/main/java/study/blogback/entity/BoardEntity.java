package study.blogback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.blogback.dto.request.board.PostBoardRequestDto;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;

    private String title;

    private String content;

    private LocalDateTime writeDateTime;

    private int favoriteCount;

    private int commentCount;

    private int viewCount;

    private String writerEmail;

    public BoardEntity(PostBoardRequestDto dto, String email) {

        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.writeDateTime = LocalDateTime.now();
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.viewCount = 0;
        this.writerEmail = email;
    }
}
