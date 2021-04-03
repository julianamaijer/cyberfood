package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.Permission;
import com.jules.cyberfood.domain.repository.PermissionRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultPermissionMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissionRepository consultPermission = applicationContext.getBean(PermissionRepository.class);
        List<Permission> permissionList = consultPermission.allPermissions();

        for (Permission permission : permissionList){
            System.out.println(permission.getName());
        }
    }
}
