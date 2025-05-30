package com.humanbooster.service;

import com.humanbooster.model.Media;
import com.humanbooster.repository.MediaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Transactional
    public void saveMedia(Media media) {
        mediaRepository.save(media);
    }

    public Optional<Media> getMediaById(int id) {
        return mediaRepository.findById(id);
    }

    public List<Media> getAllMedias() {
        return mediaRepository.findAll();
    }

    @Transactional
    public Optional<Media> updateMedia(Media media) {
        return mediaRepository.findById(media.getId()).map(existingMedia -> {
            existingMedia.setNom(media.getNom());
            existingMedia.setType(media.getType());
            existingMedia.setUrl(media.getUrl());
            existingMedia.setTaille(media.getTaille());
            return mediaRepository.save(existingMedia);
        });
    }

    @Transactional
    public Optional<Media> deleteMediaById(int id) {
        Optional<Media> media = mediaRepository.findById(id);
        media.ifPresent(mediaRepository::delete);
        return media;
    }
}
