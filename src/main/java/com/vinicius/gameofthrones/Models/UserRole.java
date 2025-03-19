package com.vinicius.gameofthrones.Models;

public enum UserRole {
    SERVER("server"),

    USER("user");


    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
