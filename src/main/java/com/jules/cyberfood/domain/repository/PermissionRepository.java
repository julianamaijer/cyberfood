package com.jules.cyberfood.domain.repository;

import com.jules.cyberfood.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> allPermissions();
    Permission addPermission(Long id);
    Permission findById(Permission permission);
    void removePermission(Permission permission);

}
