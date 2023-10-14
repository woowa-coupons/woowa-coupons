package woowa.promotion.app.member.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.app.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmail(String email);
}
