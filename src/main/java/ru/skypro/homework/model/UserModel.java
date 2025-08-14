package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "user_model")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ppk;
    @Column(nullable = false, name = "first_name", length = 16)
    @Size(min = 2, max = 16)
    private String firstName;
    @Column(nullable = false, name = "last_name", length = 16)
    @Size(min = 2, max = 16)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(example = "+7(297)98339-39")
    private String phone;
    private String image;
    @OneToMany(mappedBy = "userModel")
    private List<AdModel> adModels;
    @OneToOne
    @JoinColumn(name = "id_register")
    private RegisterUserModel registerUserModel;

    public UserModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserModel)) return false;
        UserModel userModel = (UserModel) object;
        return Objects.equals(ppk, userModel.ppk);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ppk);
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

    @Override
    public String toString() {
        return "UserModel{" +
                "ppk=" + ppk +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", adModels=" + adModels +
                ", registerUserModel=" + registerUserModel +
                '}';
    }
}

