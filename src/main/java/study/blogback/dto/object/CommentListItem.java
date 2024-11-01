package study.blogback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.blogback.repository.resultSet.GetCommentListResultSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentListItem {

    private String nickname;
    private String profileImage;
    private String content;
    private LocalDateTime writeDateTime;

    public CommentListItem(GetCommentListResultSet resultSet) {
        this.nickname = resultSet.getNickname();
        this.profileImage = resultSet.getProfileImage();
        this.content = resultSet.getContent();
        this.writeDateTime = LocalDateTime.parse(resultSet.getWriteDateTime());
    }

    public static List<CommentListItem> copyList(List<GetCommentListResultSet> resultSets) {
        List<CommentListItem> list = new ArrayList<>();
        for(GetCommentListResultSet resultSet : resultSets) {
            CommentListItem commentListItem = new CommentListItem(resultSet);
            list.add(commentListItem);
        }
        return list;
    }
}
