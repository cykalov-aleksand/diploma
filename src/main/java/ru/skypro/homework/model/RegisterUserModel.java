package ru.skypro.homework.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import ru.skypro.homework.dto.Role;

import java.util.Objects;

@Entity
@Table(name = "register_user_model")
public class RegisterUserModel {
    @Id
    @GeneratedValue
    Integer id;
    @Column(nullable = false, name = "user_name", length = 32)
    @Size(min = 4, max = 32)
    private String userName;
    @Column(length = 16)
    @Size(min = 8, max = 16)
    private String password;
    @Size(min = 8, max = 16)
    @Column(nullable = false, name = "new_password", length = 16)
    private String NewPassword;
    @Enumerated(EnumType.STRING)
    private Role role;

    public RegisterUserModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof RegisterUserModel)) return false;
        RegisterUserModel that = (RegisterUserModel) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public @Size(min = 4, max = 32) String getUserName() {
        return userName;
    }

    public void setUserName(@Size(min = 4, max = 32) String userName) {
        this.userName = userName;
    }

    public @Size(min = 8, max = 16) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, max = 16) String password) {
        this.password = password;
    }

    public @Size(min = 8, max = 16) String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(@Size(min = 8, max = 16) String newPassword) {
        NewPassword = newPassword;
    }


    @Override
    public String toString() {
        return "RegisterUserModel{" +
                "ppk=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", NewPassword='" + NewPassword + '\'' +
                ", role=" + role +
                '}';
    }
}
