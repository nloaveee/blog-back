package study.blogback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentListItem {

    private String nickname;
    private String profileImage;
    private String content;
    private LocalDateTime writeDateTime;
}
