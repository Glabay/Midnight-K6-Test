package dev.midnightcoder.photos.internal;

import dev.midnightcoder.photos.IPhotoImportService;
import dev.midnightcoder.photos.dto.PhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
class PhotoImportService implements IPhotoImportService {
    private final RestClient restClient;
    private final PhotoRepository photoRepository;

    @Override
    public void importPhotos() {
        var response = restClient.get()
            .uri("/photos")
            .retrieve()
            .body(new ParameterizedTypeReference<List<PhotoResponse>>() {});
        if (response == null || response.isEmpty()) {
            return;
        }
        var photos = response.stream()
            .map(photoResponse -> {
                var photo = new Photo();
                    photo.setId(photoResponse.id());
                    photo.setAlbumId(photoResponse.albumId());
                    photo.setTitle(photoResponse.title());
                    photo.setUrl(photoResponse.url());
                    photo.setThumbnailUrl(photoResponse.thumbnailUrl());
                return photo;
            })
            .toList();

        photoRepository.saveAll(photos);
    }
}