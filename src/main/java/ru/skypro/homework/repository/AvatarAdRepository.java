package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AvatarAdModel;
@Repository
public interface AvatarAdRepository extends JpaRepository<AvatarAdModel,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO avatar_ad (ad_pk,file_patch,file_size,media_type,data)VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
    void save(int ad_pk,String filePath, long fileSize,String mediaType, byte[] data);
   // @Query(value = "SELECT id FROM avatar_ad WHERE ad_pk= ?1", nativeQuery = true)
   // int idAvatarAd(int ad_pk);
    /**
     * Создаем SQL запрос для удаления строки из таблицы avatar_ad с указанным file_patch
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE from avatar_ad WHERE file_patch= ?1", nativeQuery = true)
    void deleteLine(String filePath);
    /**
     * подсчитываем количество avatar  в таблице
     */
    @Query(value = "SELECT count(*) FROM avatar_ad", nativeQuery = true)
    int countAvatar();

}
