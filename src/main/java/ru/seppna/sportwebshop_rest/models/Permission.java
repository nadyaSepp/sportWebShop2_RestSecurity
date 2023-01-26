package ru.seppna.sportwebshop_rest.models;


//назначения
public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),

    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
