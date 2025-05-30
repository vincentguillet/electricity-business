package com.humanbooster.controller;

import com.humanbooster.model.Media;
import com.humanbooster.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medias")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public List<Media> getAllMedias() {
        return mediaService.getAllMedias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaById(@PathVariable int id) {
        return mediaService.getMediaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveMedia(@RequestBody Media media) {
        mediaService.saveMedia(media);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Media> updateMedia(@PathVariable int id, @RequestBody Media media) {
        return mediaService.getMediaById(id)
                .map(existingMedia -> mediaService.updateMedia(media)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Media> deleteMediaById(@PathVariable int id) {
        return mediaService.deleteMediaById(id)
                .map(media -> ResponseEntity.ok().body(media))
                .orElse(ResponseEntity.notFound().build());
    }
}