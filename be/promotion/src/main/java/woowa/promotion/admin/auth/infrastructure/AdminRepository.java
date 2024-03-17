package woowa.promotion.admin.auth.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.admin.auth.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByEmail(String email);

    Optional<Admin> findByEmail(String email);

}
