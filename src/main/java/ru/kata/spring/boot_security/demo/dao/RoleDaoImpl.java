package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(long roleId) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.id = :roleId", Role.class)
                .setParameter("roleId", roleId)
                .getSingleResult();
    }

    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        return query.setParameter("name", name).getSingleResult();
    }
}
