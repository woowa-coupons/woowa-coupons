package woowa.promotion.admin.admin.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.admin.admin.domain.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByEmail(String email);

    Optional<Admin> findByEmail(String email);

}
