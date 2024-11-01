package study.blogback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.blogback.dto.request.board.PostCommentRequestDto;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="comment")
@Table(name="comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String content;

    private LocalDateTime writeDateTime;

    private String userEmail;

    private int boardId;

    public CommentEntity(PostCommentRequestDto dto, Integer boardId, String email) {
        this.content = dto.getContent();
        this.writeDateTime = LocalDateTime.now();
        this.userEmail = email;
        this.boardId = boardId;
    }
}
