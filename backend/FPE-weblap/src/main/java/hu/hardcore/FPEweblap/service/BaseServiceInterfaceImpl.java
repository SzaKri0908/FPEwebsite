package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.BaseEntity;
import hu.hardcore.FPEweblap.model.CreatedInterface;
import hu.hardcore.FPEweblap.model.UpdatedInterface;
import hu.hardcore.FPEweblap.util.SecurityUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public abstract class BaseServiceInterfaceImpl<E extends BaseEntity> implements BaseServiceInterface<E> {

    protected Class<E> entityClass;

    @Autowired
    private EntityManager entityManager;


    protected BaseServiceInterfaceImpl(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }


    @Transactional
    public E findById(Long id) {
        return (E) getEntityManager().find(entityClass, id);
    }

    @Transactional
    public void save(E entity) {
        setCreatedFields(entity);
        entityManager.persist(entity);
    }

    private void setCreatedFields(E entity){
        if (entity instanceof UpdatedInterface) {
            ((UpdatedInterface) entity).setUpdatedBy(SecurityUtil.getLoggedInUserName());
            ((UpdatedInterface) entity).setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        }
        if (entity instanceof CreatedInterface && (((CreatedInterface) entity).getCreatedBy() == null || ((CreatedInterface) entity).getCreatedBy().equals(""))) {
            ((CreatedInterface) entity).setCreatedBy(SecurityUtil.getLoggedInUserName());
            ((CreatedInterface) entity).setCreatedOn(new Timestamp(System.currentTimeMillis()));

            ((UpdatedInterface) entity).setUpdatedBy(SecurityUtil.getLoggedInUserName());
            ((UpdatedInterface) entity).setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        }
    }

    @Transactional
    public void update(E entity) {
        setCreatedFields(entity);
        getEntityManager().merge(entity);
    }

    public List<E> findAll() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<E> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(root);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    //Logikai törlést csinálunk, ahol egy flaget átállítunk töröltre. A JPA Criteria a megadott entityClass alapján
    //tudni fogja mit kell törölni.
    protected void doDelete(Long id, Integer deleted) {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<E> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(entityClass);
        Root<E> root = criteriaUpdate.from(entityClass);

        criteriaUpdate.set("deleted", deleted).where(criteriaBuilder.equal(root.get("id"), id));
        criteriaUpdate.set("updatedBy", SecurityUtil.getLoggedInUserName()).where(criteriaBuilder.equal(root.get("id"), id));
        criteriaUpdate.set("updatedOn", new Timestamp(System.currentTimeMillis())).where(criteriaBuilder.equal(root.get("id"),id));
        getEntityManager().createQuery(criteriaUpdate).executeUpdate();
    }


}
