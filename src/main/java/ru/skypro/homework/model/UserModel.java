package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.dto.Role;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "user_model")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, name = "user_name")
    private String userName;
    private String password;
    private String email;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    private String phone;
    private String image;
    @Enumerated(EnumType.STRING)
    private Role role;
    public UserModel(){}


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserModel userModel)) return false;
        return Objects.equals(id, userModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", role=" + role +
                '}';
    }
}

