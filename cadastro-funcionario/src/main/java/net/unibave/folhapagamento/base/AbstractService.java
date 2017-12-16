package net.unibave.folhapagamento.base;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractService<T, PK> {

    @Inject
    private EntityManager em;

    public T save(T entity) {
        T merged = em.merge(entity);
        return merged;
    }

    public void delete(PK id) {
        T entity = em.find(getEntityClass(), id);
        em.remove(entity);
    }

    private Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

}
