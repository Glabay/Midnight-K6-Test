package dev.midnightcoder.photos.internal;

import dev.midnightcoder.photos.IPhotoService;
import dev.midnightcoder.photos.dto.PhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class PhotoService implements IPhotoService {
    private final PhotoRepository photoRepository;

    public List<PhotoResponse> getAllPhotosForAlbum(
        Long albumId,
        Integer page,
        Integer size
    ) {
        var pageable = Pageable.ofSize(size).withPage(page);
        return photoRepository.findByAlbumId(albumId, pageable)
            .stream()
            .map(this::createPhotoResponse)
            .toList();
    }

    private PhotoResponse createPhotoResponse(Photo photo) {
        return new PhotoResponse(
            photo.getId(),
            photo.getAlbumId(),
            photo.getTitle(),
            photo.getUrl(),
            photo.getThumbnailUrl()
        );
    }
}