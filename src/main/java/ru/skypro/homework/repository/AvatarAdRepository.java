package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AvatarAdModel;
@Repository
public interface AvatarAdRepository extends JpaRepository<AvatarAdModel,Integer> {
}
