package study.blogback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
