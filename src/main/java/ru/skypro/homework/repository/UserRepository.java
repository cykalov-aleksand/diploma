package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.UserModel;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_model (first_name,last_name,phone,role)VALUES (?1,?2,?3,?4)", nativeQuery = true)
    void saveRule(String firstName, String lastName, String phone, String role);

}
