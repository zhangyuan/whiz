package whiz.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import whiz.entities.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class MessageRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Message message) {
        entityManager.persist(message);
    }

    public List<Message> findAll(int max) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> from = cq.from(Message.class);
        cq.select(from);
        cq.orderBy(cb.desc(from.get("id")));
        return entityManager.
                createQuery(cq)
                .setFirstResult(0)
                .setMaxResults(max)
                .getResultList();
    }

    public List<Message> findByToUser(String username, int max) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> from = cq.from(Message.class);
        cq.select(from);
        cq.where(cb.equal(from.get("to"), username));
        cq.orderBy(cb.desc(from.get("id")));
        return entityManager.
                createQuery(cq)
                .setFirstResult(0)
                .setMaxResults(max)
                .getResultList();
    }
}
