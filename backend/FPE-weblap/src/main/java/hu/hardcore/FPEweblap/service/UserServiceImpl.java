package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceInterfaceImpl<User> implements UserService{
    protected UserServiceImpl() {
        super(User.class);
    }

    @Override
    public User findUserByUsername(String username) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(criteriaBuilder.like(root.get("username"), username));
        User result = getEntityManager().createQuery(criteriaQuery).getSingleResult();
        return result;
    }
}
