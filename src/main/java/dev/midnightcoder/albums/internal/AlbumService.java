package dev.midnightcoder.albums.internal;

import dev.midnightcoder.albums.IAlbumService;
import dev.midnightcoder.albums.dto.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService implements IAlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public List<AlbumResponse> getAllAlbumsForUser(Long userId, Integer page, Integer size) {
        var pageable = Pageable.ofSize(size).withPage(page);
        return albumRepository.findByUserId(userId, pageable)
            .stream()
            .map(this::mapToAlbumResponse)
            .toList();
    }

    @Override
    public AlbumResponse getAlbumById(Long albumId) {
        return albumRepository.findById(albumId)
            .stream()
            .map(this::mapToAlbumResponse)
            .findFirst()
            .orElseThrow(() ->
                new IllegalArgumentException("Album not found with id: " + albumId));
    }

    private AlbumResponse mapToAlbumResponse(Album album) {
        return new AlbumResponse(
            album.getId(),
            album.getUserId(),
            album.getTitle()
        );
    }
}