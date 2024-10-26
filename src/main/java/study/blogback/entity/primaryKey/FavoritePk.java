package study.blogback.entity.primaryKey;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoritePk implements Serializable {

    @Column(name="userEmail")
    private String userEmail;

    @Column(name="boardId")
    private int boardId;
}
