package gouri.ibk.interactivetrader.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractEntity<Key> {

    protected abstract Key getId();

    @PersistenceContext
    EntityManager entityManager;

    public void save() {
        entityManager.persist(this);
    }

    public <T extends AbstractEntity<Key>> T findById(Key id) {
        return (T) entityManager.find(this.getClass(), id);
    }

    public <T extends AbstractEntity<Key>> T findByMyKey() {
        return (T) entityManager.find(this.getClass(), this.getId());
    }


}
