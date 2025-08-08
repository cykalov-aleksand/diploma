package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.AvatarAdModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.AvatarAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;
    private final AvatarAdRepository avatarAdRepository;
    private final AvatarComponent avatarComponent;

    public AdService(AdRepository adRepository, UserRepository userRepository, AuthServiceImpl authService,
                     AvatarAdRepository avatarAdRepository, AvatarComponent avatarComponent) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.avatarAdRepository = avatarAdRepository;
        this.avatarComponent = avatarComponent;
    }

    public Ads getAllService() {
        List<AdModel> adList = adRepository.findAll();
        List<Ad> ad = new ArrayList<>();
        for (AdModel variable : adList) {
            ad.add(new Ad(adRepository.author(variable.getPk()), variable.getImage(), variable.getPk(),
                    variable.getTitle(), variable.getPrice()));
        }

        return new Ads(adRepository.countAdList(), ad);
    }

    public ExtendedAd getInformationAboutAd(int id) {
        AdModel adModel = adRepository.getInformationAboutAdModel(id);
        UserModel userModel = userRepository.userModelFindId(adRepository.author(id));
        return new ExtendedAd(adModel.getPk(), userModel.getFirstName(), userModel.getLastName(),
                adModel.getDescription(), userModel.getEmail(), adModel.getImage(), userModel.getPhone(),
                adModel.getPrice(), adModel.getTitle());

    }

    public ResponseEntity<Void> deleteAd(int id) throws IOException {
        String filePath = adRepository.findPkImageAvatar(id);
        if (filePath != null) {
            avatarComponent.delete(filePath);
        }
        avatarAdRepository.deleteLine(filePath);
        adRepository.deleteAd(id);

        return ResponseEntity.ok().build();
    }

    public Ad updatingInformationAboutAd(int id, CreateOrUpdateAd createOrUpdateAd) {
        adRepository.updatingUserInformationAd(createOrUpdateAd.getTitle(), createOrUpdateAd.getDescription(),
                createOrUpdateAd.getPrice(), id);
        AdModel adModelAbout = adRepository.getInformationAboutAdModel(id);
        return new Ad(adRepository.author(id), adModelAbout.getImage(), adModelAbout.getPk(),
                adModelAbout.getTitle(), adModelAbout.getPrice());
    }

    public Ads getAdsFromAuthorized() {
        UserModel userModel = userRepository.informationAboutUser(authService.usernameAuthorised());
        List<AdModel> adList = adRepository.findListPkAdModel(userModel.getId());
        List<Ad> ad = new ArrayList<>();
        for (AdModel variable : adList) {
            ad.add(new Ad(adRepository.author(variable.getPk()), variable.getImage(), variable.getPk(),
                    variable.getTitle(), variable.getPrice()));
        }
        return new Ads(adRepository.countAdListAuthor(userModel.getId()), ad);
    }

    public ResponseEntity<String> UpdatingAdImage(int id, MultipartFile image) {
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<Ad> addingAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException {
        if (image.getSize() >= 1024 * 300) {
            return ResponseEntity.status(403).build();
        }
        UserModel userModel = userRepository.informationAboutUser(authService.usernameAuthorised());
        AdModel advertisementAd = new AdModel();
        advertisementAd.setTitle(createOrUpdateAd.getTitle());
        advertisementAd.setPrice(createOrUpdateAd.getPrice());
        advertisementAd.setDescription(createOrUpdateAd.getDescription());
        Path filePath = avatarComponent.saveAvatar("avatar/ad", authService.usernameAuthorised(),
                advertisementAd.getTitle() +
                        " (avatar N " + (avatarAdRepository.countAvatar() + 1) + ")", image);
        AvatarAdModel avatar = new AvatarAdModel();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatar.setData(avatarComponent.generateImagePreview(filePath));
        adRepository.saveAd(userModel.getId(), advertisementAd.getTitle(),
                advertisementAd.getDescription(), advertisementAd.getPrice());
        int pk = adRepository.findPk(userModel.getId(), advertisementAd.getTitle(),
                advertisementAd.getDescription(), advertisementAd.getPrice());
        avatarAdRepository.save(pk, avatar.getFilePath(), avatar.getFileSize(), avatar.getMediaType(), avatar.getData());
        adRepository.updateImage(avatar.getFilePath(), pk);
        AdModel out = adRepository.findPkObject(pk);
        return ResponseEntity.status(201).body(new Ad(userModel.getId(),
                out.getImage(), out.getPk(), out.getTitle(), out.getPrice()));
    }
}
