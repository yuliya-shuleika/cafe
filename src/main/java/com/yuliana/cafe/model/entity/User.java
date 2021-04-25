package com.yuliana.cafe.model.entity;

/**
 * User entity.
 *
 * @author Yulia Shuleiko
 */
public class User {

    private int userId;
    private String name;
    private String email;
    private UserRole role;
    private UserStatus status;
    private String avatar;

    /**
     * Constructs the {@code User} object with given name, email, role, status and avatar.
     *
     * @param name name of the user
     * @param email email of the user
     * @param role {@code UserRole} object represents role of the user
     * @param status {@code UserStatus} object represents status of the user
     * @param avatar filepath of the avatar of the user
     */
    public User(String name, String email, UserRole role, UserStatus status, String avatar) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
    }

    /**
     * Constructs the {@code User} object with given name, email, role, status and avatar.
     * @param name name of the user
     * @param email email of the user
     * @param role {@code UserRole} object represents role of the user
     * @param status {@code UserStatus} object represents status of the user
     */
    public User(String name, String email, UserRole role, UserStatus status) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    /**
     * Constructs the {@code User} object with given id, name, email, role, status and avatar.
     *
     * @param userId id of the user
     * @param name name of the user
     * @param email email of the user
     * @param role {@code UserRole} object represents role of the user
     * @param status {@code UserStatus} object represents status of the user
     * @param avatar filepath of the avatar of the user
     */
    public User(int userId, String name, String email, UserRole role, UserStatus status, String avatar) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
    }

    /**
     * Constructs the {@code User} object
     */
    public User() {
    }

    /**
     * Getter method of the user's id.
     *
     * @return id of the user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter method of the user's id.
     *
     * @param userId id of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter method of the name.
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method of the name.
     *
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method of the email.
     *
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method of the email.
     *
     * @param email email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method of the role.
     *
     * @return {@code UserRole} object represents role of the user
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Setter method of the role.
     *
     * @param role {@code UserRole} object represents role of the user
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Getter method of the status.
     *
     * @return {@code UserStatus} object represents status of the user
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Setter method of the status.
     *
     * @param status {@code UserStatus} object represents status of the user
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Getter method of the avatar.
     *
     * @return filepath of the avatar of the user
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Setter method of the avatar.
     *
     * @param avatar filepath of the avatar of the user
     */
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

