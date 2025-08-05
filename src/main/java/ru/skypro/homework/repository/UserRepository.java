package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    /**
     * Создаем SQL запрос на добавление строки при проведении авторизации
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_model (password,first_name,last_name,phone,role)VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
    void saveRule(String password,String firstName, String lastName, String phone, String role);
    /**
     * Создаем SQL запрос для поиска строки содержащей указанный пароль
     * */
    @Query(value = "SELECT * FROM user_model WHERE password= ?1", nativeQuery = true)
    UserModel findIdPassword(String password);
    /**
     * Создаем SQL запрос для проведения корректировки пароля по указанному id
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET password=?1 WHERE id=?2", nativeQuery = true)
    void updatePassword(String password, Integer id);
    /**
     * Создаем SQL запрос для поиска строки с указанным userName
     */
    @Query(value = "SELECT * FROM user_model WHERE user_name= ?1", nativeQuery = true)
    UserModel informationAboutUser(String userName);
    /**
     * Создаем SQL запрос для поиска строки по userName и password
     */
    @Query(value = "SELECT COUNT(*) FROM user_model WHERE user_name= ?1 AND password= ?2", nativeQuery = true)
    int availabilityInDatabase(String userName,String password);
    /**
     * Создаем SQL запрос для записи значений user_name и password в новую строку (используется при отсутствии
     * информации в БД по авторизованному пользователю
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_model (user_name,password)VALUES (?1,?2)", nativeQuery = true)
    void saveUserPassword(String userName,String password);
    /**
     * Создаем SQL запрос для проведения корректировки значений  first_name, last_name, phone по указанному user_name
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET first_name=?1, last_name=?2, phone=?3 WHERE user_name=?4", nativeQuery = true)
    void updatingUserInformationAuthorised(String firstName,String lastName,String phone,String userName);
    /**
     * Создаем SQL запрос для проведения корректировки ссылки на avatar по указанному user_name
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET image=?1 WHERE user_name=?2", nativeQuery = true)
    void updateImage(String image, String userName);

}
