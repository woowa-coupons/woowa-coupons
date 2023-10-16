package woowa.promotion.admin.admin.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import woowa.promotion.admin.admin.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByEmail(String email);

}
