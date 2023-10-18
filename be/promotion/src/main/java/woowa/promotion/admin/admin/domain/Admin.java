package woowa.promotion.admin.admin.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowa.promotion.global.domain.audit.AuditingFields;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String nickname;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 500)
    private String password;

    private Admin(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public static Admin of(String nickname, String email, String password) {
        return new Admin(nickname, email, password);
    }

    public boolean isSamePassword(String password) {
        return this.password.equals(password);
    }
}
