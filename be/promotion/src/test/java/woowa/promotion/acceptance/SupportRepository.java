package woowa.promotion.acceptance;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Component
@Transactional
public class SupportRepository {

    @Autowired
    private EntityManager em;

    public <T> T save(T entity) {
        em.persist(entity);
        em.flush();
        em.clear();
        return entity;
    }

    public <T> List<T> findAll(Class<T> entityType) {
        em.clear();
        String entityTypeName = entityType.getSimpleName();
        return em.createQuery(String.format("SELECT entity FROM %s entity", entityTypeName), entityType)
                .getResultList();
    }
}
