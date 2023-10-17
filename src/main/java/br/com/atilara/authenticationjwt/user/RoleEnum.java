package br.com.atilara.authenticationjwt.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public enum RoleEnum {

    USER(Collections.emptySet()),

    MANAGER(
            Set.of(
                PermissionEnum.MANAGER_CREATE,
                PermissionEnum.MANAGER_READ,
                PermissionEnum.MANAGER_UPDATE,
                PermissionEnum.MANAGER_DELETE
            )
    ),

    ADMIN(
            Set.of(
                PermissionEnum.MANAGER_CREATE,
                PermissionEnum.MANAGER_READ,
                PermissionEnum.MANAGER_UPDATE,
                PermissionEnum.MANAGER_DELETE,
                PermissionEnum.ADMIN_CREATE,
                PermissionEnum.ADMIN_READ,
                PermissionEnum.ADMIN_UPDATE,
                PermissionEnum.ADMIN_DELETE
            )
    );

    @Getter
    private final Set<PermissionEnum> permissions;

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        var authorities = new java.util.ArrayList<>(permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
