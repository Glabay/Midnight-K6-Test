package dev.midnightcoder.photos;

import dev.midnightcoder.photos.dto.PhotoResponse;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
public interface IPhotoService {
    List<PhotoResponse> getAllPhotosForAlbum(Long albumId, Integer page, Integer size);
}
