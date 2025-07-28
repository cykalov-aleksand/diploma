package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.CreateOrUpdateAd;
import ru.skypro.homework.model.dto.Ad;
import ru.skypro.homework.model.dto.Ads;
import ru.skypro.homework.model.dto.ExtendedAd;

@Service
public class AdService {
    public AdService() {
    }

    public Ads getAllService(){

        return new Ads();
}
public ExtendedAd getInformationAboutAd(int id){
        return new ExtendedAd(1,"автор","фамилия","щбъявление","mail.ru","fdgdfg","23213",12321,"dfgdfg");
}
public ResponseEntity<Void> deleteAd(int id){
        return ResponseEntity.ok().build();
}
public Ad updatingInformationAboutAd(int id, CreateOrUpdateAd createOrUpdateAd){
        return null;
}
    public Ads getAdsFromAuthorized(){
        return null;
    }
    public void UpdatingAdImage(int id, MultipartFile image){

    }
    public Ad addingAd(CreateOrUpdateAd createOrUpdateAd,MultipartFile image){
        return null;
    }
}
