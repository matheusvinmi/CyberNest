package br.com.CyberNest.infra;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(String role){
        return role;
    }
}
