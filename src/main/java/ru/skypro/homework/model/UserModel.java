package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.dto.Role;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "user_model")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, name = "user_name",length = 32)
    @Size(min = 4, max = 32)
    private String userName;
    @Column(length = 16)
    @Size(min = 8, max = 16)
    private String password;
    private String email;
    @Column(nullable = false, name = "first_name",length = 16)
    @Size(min = 2, max = 16)
    private String firstName;
    @Column(nullable = false, name = "last_name",length = 16)
    @Size(min = 2, max = 16)
    private String lastName;
    @Pattern(regexp ="\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(example = "+7(297)98339-39")
    private String phone;
    private String image;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "userModel")
    private List<AdModel>adModels;
    public UserModel(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @Size(min = 2, max = 16) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 2, max = 16) String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 2, max = 16) String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 2, max = 16) String lastName) {
        this.lastName = lastName;
    }

    public @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<AdModel> getAdModels() {
        return adModels;
    }

    public void setAdModels(List<AdModel> adModels) {
        this.adModels = adModels;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserModel)) return false;
        UserModel userModel = (UserModel) object;
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

