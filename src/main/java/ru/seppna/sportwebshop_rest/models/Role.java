package ru.seppna.sportwebshop_rest.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    CLIENT(Set.of(Permission.USER_READ,Permission.PRODUCT_READ)),

    ADMIN(Set.of(Permission.USER_READ,
                 Permission.USER_WRITE,
                 Permission.PRODUCT_READ,
                 Permission.PRODUCT_WRITE)),

    SUPERADMIN(Set.of(Permission.USER_READ,
                      Permission.USER_WRITE,
                      Permission.PRODUCT_READ,
                      Permission.PRODUCT_WRITE,
                      Permission.ROLE_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
