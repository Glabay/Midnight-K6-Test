package dev.midnightcoder.posts.internal;

import dev.midnightcoder.posts.IPostImportService;
import dev.midnightcoder.posts.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
@Service
@RequiredArgsConstructor
class PostImportService implements IPostImportService {
    private final RestClient restClient;
    private final PostRepository postRepository;

    @Override
    public void importPosts() {
        var response = restClient.get()
            .uri("/posts")
            .retrieve()
            .body(new ParameterizedTypeReference<List<PostResponse>>() {});
        if (response == null || response.isEmpty()) {
            return;
        }

        var posts = response.stream()
            .map(post -> {
                var model = new Post();
                    model.setId(post.id());
                    model.setUserId(post.userId());
                    model.setTitle(post.title());
                    model.setBody(post.body());
                return model;
            })
            .toList();
        postRepository.saveAll(posts);
    }
}
