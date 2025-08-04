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
    @Query(value = "INSERT INTO user_model (password,first_name,last_name,phone,role)VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
    void saveRule(String password,String firstName, String lastName, String phone, String role);
    @Query(value = "SELECT * FROM user_model WHERE password= ?1", nativeQuery = true)
    UserModel findIdPassword(String password);
    /**
     * Создаем SQL запрос для увеличения значения ячейки count на 1 по строке с dinamic_id равном указанному значению
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET password=?1 WHERE id=?2", nativeQuery = true)
    void updatePassword(String password, Integer id);
    @Query(value = "SELECT * FROM user_model WHERE user_name= ?1", nativeQuery = true)
    UserModel InformationAboutUser(String userName);

    @Query(value = "SELECT COUNT(*) FROM user_model WHERE user_name= ?1 AND password= ?2", nativeQuery = true)
    int availabilityInDatabase(String userName,String password);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_model (user_name,password)VALUES (?1,?2)", nativeQuery = true)
    void saveUserPassword(String userName,String password);

}
