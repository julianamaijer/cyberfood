package com.jules.cyberfood.infrastructure.repository;

import com.jules.cyberfood.domain.model.Permission;
import com.jules.cyberfood.domain.repository.PermissionRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Permission> allPermissions() {
        TypedQuery<Permission> query = entityManager.createQuery("from Permission", Permission.class);
        return query.getResultList();
    }

    @Override
    public Permission addPermission(Long id) {
        return entityManager.find(Permission.class, id);
    }

    @Override
    public Permission findById(Permission permission) {
        return entityManager.merge(permission);
    }

    @Override
    public void removePermission(Permission permission) {
        permission = findById(permission);
        entityManager.remove(permission);
    }
}
