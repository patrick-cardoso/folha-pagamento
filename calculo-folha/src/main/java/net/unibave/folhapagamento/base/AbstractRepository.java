package net.unibave.folhapagamento.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractRepository<T, PK> { // TODO: 13/11/17 verificar pq PK nao funciona nos metodos

    @PersistenceContext
    private EntityManager em;

    private Class<T> tClass;

    public EntityManager getEntityManager() {
        return em;
    }

    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getClazz());
        criteria.from(getClazz());
        return em.createQuery(criteria)
                .getResultList();
    }

    public T find(PK id) {
        T entity = em.find(getClazz(), id);
        return entity;
    }

    public T findOrThrow(PK id) {
        T entity = find(id);
        if (entity == null) {
            throw new EntityNotFoundException("Entidade com id " + id.toString() + " não encontrada.");
        }
        return entity;
    }

    public T findReferenceOrThrow(PK id) {
        T entity = em.getReference(getClazz(), id);
        return entity;
    }

    private Class<T> getClazz() {
        if (tClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            tClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return tClass;
    }

}
