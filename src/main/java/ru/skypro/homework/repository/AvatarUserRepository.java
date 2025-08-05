package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AvatarUserModel;
@Repository
public interface AvatarUserRepository extends JpaRepository<AvatarUserModel,Integer> {
    @Query(value = "SELECT id FROM avatar_user WHERE user_id= ?1", nativeQuery = true)
    Integer findUserAvatar(Integer userId);

     @Transactional
     @Modifying
    @Query(value = "INSERT INTO avatar_user (file_patch,file_size,media_type,data,user_id)VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
    void save(String filePath, long fileSize,String mediaType, byte[] data,int userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM avatar_user WHERE user_id= ?1", nativeQuery = true)
    void deleteLine(int id);
}
