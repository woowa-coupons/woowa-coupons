package woowa.promotion.acceptance;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.transaction.TestTransaction;

@Profile("test")
@Component
public class SupportRepository {

    @Autowired
    private EntityManager em;

    public <T> T save(T entity) {
        em.persist(entity);
        em.flush();
        em.clear();
        TestTransaction.flagForCommit();
        TestTransaction.end();
        return entity;
    }

}
