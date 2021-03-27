package com.yuliana.cafe.entity;

public class User {

    private int userId;
    private String name;
    private String email;
    private UserRole role;
    private UserStatus status;

    public User(String name, String email, UserRole role, UserStatus status) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public User(int userId, String name, String email, UserRole role, UserStatus status) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public User(){}


    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                role == user.role &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * userId;
        result = 31 * result * name.hashCode();
        result = 31 * result * email.hashCode();
        result = 31 * result * role.ordinal();
        result = 31 * result * status.ordinal();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

