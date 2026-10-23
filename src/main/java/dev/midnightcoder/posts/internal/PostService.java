package dev.midnightcoder.posts.internal;

import dev.midnightcoder.posts.IPostService;
import dev.midnightcoder.posts.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
class PostService implements IPostService {
    private final PostRepository postRepository;

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    @Override
    public List<PostResponse> getPostsByUserId(Long userId, Integer pageId, Integer size) {
        var pageable = Pageable.ofSize(size).withPage(pageId);
        var cachedPost = postRepository.findByUserId(userId, pageable);
        return cachedPost.stream()
            .map(this::mapToResponse)
            .toList();
    }

    @Override
    public Long getPostCountForUser(Long userId) {
        return postRepository.countPostByUserId(userId);
    }

    @Override
    public List<PostResponse> getHomePagePosts(Integer pageId, Integer size) {
        var pageable = Pageable.ofSize(size).withPage(pageId);
        var pagedResults = postRepository.findAll(pageable);
        return pagedResults
            .map(this::mapToResponse)
            .toList();
    }

    private PostResponse mapToResponse(Post post) {
        return new PostResponse(
            post.getId(),
            post.getUserId(),
            post.getTitle(),
            post.getBody()
        );
    }
}
