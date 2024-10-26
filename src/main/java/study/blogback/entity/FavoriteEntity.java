package study.blogback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.blogback.entity.primaryKey.FavoritePk;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="favorite")
@Table(name="favorite")
@IdClass(FavoritePk.class)
public class FavoriteEntity {

    @Id
    private String userEmail;

    @Id
    private int boardId;
}
