package study.blogback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.blogback.dto.request.auth.SignUpRequestDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {

    @Id
    private String email;

    private String userId;

    private String password;

    private String nickname;

    private String telNumber;

    private String address;

    private String addressDetail;

    private String profileImage;

    private boolean agreedPersonal;

    private String role;

    private String type;

    public UserEntity(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.agreedPersonal = dto.getAgreedPersonal();
        this.type="app";
        this.role="ROLE_USER";
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
