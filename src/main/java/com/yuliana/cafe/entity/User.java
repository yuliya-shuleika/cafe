package com.yuliana.cafe.entity;

public class User {

    private int userId;
    private String name;
    private String email;
    private UserRole role;
    private UserStatus status;
    private String avatar;

    public User(String name, String email, UserRole role, UserStatus status, String avatar) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
    }

    public User(String name, String email, UserRole role, UserStatus status) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public User(int userId, String name, String email, UserRole role, UserStatus status, String avatar) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
    }

    public User() {
    }


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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (userId != user.userId) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        if (status != user.status) return false;
        return avatar.equals(user.avatar);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + avatar.hashCode();
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
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

