package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.Ad;
import ru.skypro.homework.model.dto.Ads;
import ru.skypro.homework.model.CreateOrUpdateAd;
import ru.skypro.homework.model.dto.ExtendedAd;

public interface AdsService {
    Ads getAllService();
    ResponseEntity<ExtendedAd>getInformationAboutAd(int id);
    ResponseEntity<Void>deleteAd(int id);
ResponseEntity<Ad>updatingInformationAboutAd(int id, CreateOrUpdateAd createOrUpdateAd);
    Ads getAdsFromAuthorized();
    void UpdatingAdImage(int id, MultipartFile image);
    Ad addingAd(CreateOrUpdateAd createOrUpdateAd,MultipartFile image);

}
