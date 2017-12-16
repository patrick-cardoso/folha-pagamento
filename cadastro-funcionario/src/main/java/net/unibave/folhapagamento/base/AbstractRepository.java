package net.unibave.folhapagamento.base;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractRepository<T, PK> {

    @Inject
//    @Oracle
    private EntityManager em;

    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
        criteria.from(getEntityClass());
        return em.createQuery(criteria).getResultList();
    }

    public T find(PK id) {
        T entity = em.find(getEntityClass(), id);
        if (entity == null) {
            String message = "Entidade com id " + id + " não encontrada";
            throw new EntityNotFoundException(message);
        }
        return entity;
    }

    private Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

}
