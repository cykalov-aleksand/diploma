package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter

@Table(name = "avatar_ad")
@Entity
public class AvatarAdModel {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, name = "file_patch")
    private String filePath;
    @Column(nullable = false, name = "media_type")
    private String mediaType;
    @Column(nullable = false, name = "file_size")
    private long fileSize;
    @Lob
    @Column( name = "data")
    private byte[] data;
    @OneToOne
    @JoinColumn(name = "ad_pk")
     private AdModel adModel;
public AvatarAdModel(){}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AvatarAdModel)) return false;
        AvatarAdModel that = (AvatarAdModel) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AvatarAdModel{" +
                "adModel=" + adModel +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", id=" + id +
                '}';
    }
}
