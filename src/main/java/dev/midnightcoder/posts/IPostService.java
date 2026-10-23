package dev.midnightcoder.posts;

import dev.midnightcoder.posts.dto.PostResponse;

import java.util.List;

/**
 * @author Glabay | The Midnight Coder
 * @project Midnight K6 Test
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-10-20
 */
public interface IPostService {
    List<PostResponse> getAllPosts();
    List<PostResponse> getHomePagePosts(Integer pageId, Integer size);
    List<PostResponse> getPostsByUserId(Long userId, Integer pageId, Integer size);
    Long getPostCountForUser(Long userId);
}
