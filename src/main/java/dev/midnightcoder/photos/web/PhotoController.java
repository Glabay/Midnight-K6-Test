package dev.midnightcoder.photos.web;

import dev.midnightcoder.albums.IAlbumService;
import dev.midnightcoder.photos.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/photos")
public class PhotoController {
    private final IAlbumService albumService;
    private final IPhotoService photoService;

    @GetMapping("/album/{albumId}")
    public String getPhoto(@PathVariable Long albumId,
                           @RequestParam(
                               required = false,
                               defaultValue = "0"
                           ) Integer page,
                           @RequestParam(
                               required = false,
                               defaultValue = "16"
                           ) Integer size,
                           Model model
    ) {
        var album = albumService.getAlbumById(albumId);
        var photos = photoService.getAllPhotosForAlbum(albumId, page, size);
        model.addAttribute("photos", photos);
        model.addAttribute("album", album);
        return "fragments/photos :: photos";
    }
}