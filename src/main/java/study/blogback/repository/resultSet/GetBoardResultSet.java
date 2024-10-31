package study.blogback.repository.resultSet;

public interface GetBoardResultSet {
    Integer getBoardId();
    String getTitle();
    String getContent();
    String getWriteDateTime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();
}
