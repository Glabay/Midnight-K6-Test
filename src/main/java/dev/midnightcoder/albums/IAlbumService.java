package dev.midnightcoder.albums;

import dev.midnightcoder.albums.dto.AlbumResponse;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
public interface IAlbumService {
    List<AlbumResponse> getAllAlbumsForUser(Long userId, Integer page, Integer size);
    AlbumResponse getAlbumById(Long albumId);
}
