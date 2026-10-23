package dev.midnightcoder.albums.internal;

import dev.midnightcoder.albums.IAlbumImportService;
import dev.midnightcoder.albums.dto.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumImportService implements IAlbumImportService {
    private final RestClient restClient;
    private final AlbumRepository albumRepository;

    public void importAlbums() {
        var response = restClient.get()
            .uri("/albums")
            .retrieve()
            .body(new ParameterizedTypeReference<List<AlbumResponse>>() {});
        if (response == null || response.isEmpty()) {
            return;
        }
        var albums = response.stream()
            .map(albumResponse -> {
                var album = new Album();
                    album.setId(albumResponse.id());
                    album.setUserId(albumResponse.userId());
                    album.setTitle(albumResponse.title());
                return album;
            })
            .toList();

        albumRepository.saveAll(albums);
    }

}